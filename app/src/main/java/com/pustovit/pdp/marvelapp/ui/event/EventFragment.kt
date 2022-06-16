package com.pustovit.pdp.marvelapp.ui.event

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.pustovit.pdp.marvelapp.R
import com.pustovit.pdp.marvelapp.app.appComponent
import com.pustovit.pdp.marvelapp.common.delegate.CompositeDisposableDelegate
import com.pustovit.pdp.marvelapp.databinding.FragmentCharactersBinding
import com.pustovit.pdp.marvelapp.databinding.FragmentEventBinding
import com.pustovit.pdp.marvelapp.ui.characters.CharactersFragment
import com.pustovit.pdp.marvelapp.ui.characters.CharactersListAdapter
import com.pustovit.pdp.marvelapp.ui.event.di.ViewModelFactory
import com.pustovit.pdp.marvelapp.ui.common.extensions.handleViewStateError
import com.pustovit.pdp.marvelapp.ui.event.di.DaggerEventComponent
import com.pustovit.pdp.marvelapp.ui.event.mvi.EventViewState
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class EventFragment : Fragment() {

    private var binding: FragmentEventBinding? = null

    private val compositeDisposable by CompositeDisposableDelegate()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var imageLoader: ImageLoader

    private val adapter: CharactersListAdapter by lazy {
        CharactersListAdapter(imageLoader)
    }

    private val viewModel: EventViewModel by viewModels<EventViewModel> {
        viewModelFactory
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerEventComponent.builder()
            .appComponent(appComponent())
            .build().inject(this)
        viewModel.onAttach()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = with(FragmentEventBinding.inflate(layoutInflater)) {
        binding = this
        this.root
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.let {
            initViews(it, savedInstanceState)
        }
        arguments?.getInt(ARG_KEY)?.let {
            viewModel.loadEvent(it)
        }
        observeViewState()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        binding?.recyclerView?.layoutManager?.onSaveInstanceState()?.let { state ->
            outState.putParcelable(RV_STATE, state)
        }
        super.onSaveInstanceState(outState)
    }

    private fun initViews(binding: FragmentEventBinding, savedInstanceState: Bundle?) {
        adapter.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY

        savedInstanceState?.getParcelable<Parcelable>(RV_STATE)?.let {
            binding.recyclerView.layoutManager?.onRestoreInstanceState(it)
        }
        binding.recyclerView.adapter = adapter
        adapter.onItemClick = {
            viewModel.onCharacterClick(it)
        }
    }

    private fun observeViewState() {
        viewModel.viewState
            .doOnNext(::handleViewState)
            .subscribe().addTo(compositeDisposable)
    }

    private fun handleViewState(state: EventViewState) {
        state.apply {
            handleViewStateError(viewStateError)
            adapter.submitList(characters)
            binding?.let {
                it.progressBar.visibility = if (loading) View.VISIBLE else View.GONE

                if (event.thumbnail.isValid) {

                    val request = ImageRequest.Builder(requireContext())
                        .data(event.thumbnail.url)
                        .transformations(CircleCropTransformation())
                        .target(it.eventImageView)
                        .error(R.drawable.ic_person_24)
                        .build()
                    imageLoader.enqueue(request)
                }

                it.nameTextView.text = event.title
                it.descriptionsTextView.text = event.description
            }
        }
    }

    companion object {

        private const val ARG_KEY = "event"
        private const val RV_STATE = "charactersEventsRvState"

        fun newInstance(eventId: Int): EventFragment {
            return EventFragment().apply {
                arguments = bundleOf(ARG_KEY to eventId)
            }
        }
    }
}