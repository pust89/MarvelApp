package com.pustovit.pdp.marvelapp.ui.character

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import com.pustovit.pdp.marvelapp.databinding.FragmentCharacterBinding
import com.pustovit.pdp.marvelapp.ui.character.di.DaggerCharacterComponent
import com.pustovit.pdp.marvelapp.ui.character.di.ViewModelFactory
import com.pustovit.pdp.marvelapp.ui.character.mvi.CharacterViewState
import com.pustovit.pdp.marvelapp.ui.characters.CharactersFragment
import com.pustovit.pdp.marvelapp.ui.common.extensions.handleViewStateError
import io.reactivex.rxkotlin.addTo
import timber.log.Timber
import javax.inject.Inject

class CharacterFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var imageLoader: ImageLoader

    private val viewModel by viewModels<CharacterViewModel> {
        viewModelFactory
    }

    private var binding: FragmentCharacterBinding? = null

    private val compositeDisposable by CompositeDisposableDelegate()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Timber.d("onAttach called CharacterFragment")
        DaggerCharacterComponent.builder()
            .appComponent(appComponent())
            .build().inject(this)
        viewModel.onAttach()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = with(FragmentCharacterBinding.inflate(layoutInflater)) {
        binding = this
        this.root
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt(ARG_KEY)?.let {
            viewModel.loadCharacter(it)
            binding?.let {
                initViews(it, savedInstanceState)
            }
            observeViewState()
        }
    }


    private fun initViews(binding: FragmentCharacterBinding, savedInstanceState: Bundle?) {
        binding.comicsButton.setOnClickListener {
            viewModel.onComicsButtonClick()
        }
        binding.seriesButton.setOnClickListener {
            viewModel.onSeriesButtonClick()
        }
        binding.storiesButton.setOnClickListener {
            viewModel.onStoriesButtonClick()
        }
    }

    private fun observeViewState() {
        viewModel.viewState
            .doOnNext(::handleViewState)
            .subscribe().addTo(compositeDisposable)
    }

    private fun handleViewState(state: CharacterViewState) {
        state.apply {
            handleViewStateError(viewStateError)
            binding?.let {
                it.progressBar.visibility = if (loading) View.VISIBLE else View.GONE

                if (character.thumbnail.url.isNotEmpty()) {

                    val request = ImageRequest.Builder(requireContext())
                        .data(character.thumbnail.url)
                        .transformations(CircleCropTransformation())
                        .target(it.characterImageView)
                        .error(R.drawable.ic_characters_24)
                        .build()
                    imageLoader.enqueue(request)
                }

                it.nameTextView.text = character.name
                it.descriptionsTextView.text = character.description
            }
        }
    }

    companion object {
        private const val ARG_KEY = "character"

        fun newInstance(characterId: Int): CharacterFragment {
            return CharacterFragment().apply {
                arguments = bundleOf(ARG_KEY to characterId)
            }
        }
    }
}