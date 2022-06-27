package com.pustovit.pdp.character.summary

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.pustovit.pdp.character.databinding.FragmentSummaryBinding
import com.pustovit.pdp.character.di.CharacterComponentHolder
import com.pustovit.pdp.character.di.ViewModelFactory
import com.pustovit.pdp.character.summary.mvi.SummaryViewState
import com.pustovit.pdp.common_models.domain.Items
import com.pustovit.pdp.common_models.domain.Summary
import com.pustovit.pdp.common_ui.ui.CompositeDisposableDelegate
import com.pustovit.pdp.common_ui.ui.baseViewModels
import com.pustovit.pdp.common_ui.ui.handleViewStateError
import io.reactivex.rxkotlin.addTo
import kotlinx.parcelize.Parcelize
import timber.log.Timber
import javax.inject.Inject

class SummaryFragment : Fragment() {

    private var binding: FragmentSummaryBinding? = null

    private val compositeDisposable by CompositeDisposableDelegate()

    @Inject
    lateinit var adapter: SummaryListAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by baseViewModels<SummaryViewModel> {
        viewModelFactory
    }

    private val title: SummaryTitle? by lazy {
        arguments?.getParcelable<SummaryTitle>(ARG_KEY_TITLE)
    }

    private val rvStateKey: String
        get() = "rvState".plus(title)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        CharacterComponentHolder.component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = with(FragmentSummaryBinding.inflate(layoutInflater)) {
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

        val contentList = when (title) {
            SummaryTitle.Comics -> {
                arguments?.getParcelable<Items>(ARG_KEY_CONTENT)?.items
            }
            SummaryTitle.Series -> {
                arguments?.getParcelable<Items>(ARG_KEY_CONTENT)?.items
            }
            SummaryTitle.Stories -> {
                arguments?.getParcelable<Items>(ARG_KEY_CONTENT)?.items
            }
            else -> {
                emptyList<Summary>()
            }
        }
        viewModel.setContent(title?.name, contentList)
        observeViewState()
    }

    private fun initViews(binding: FragmentSummaryBinding, savedInstanceState: Bundle?) {
        adapter.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY

        savedInstanceState?.getParcelable<Parcelable>(rvStateKey)?.let {
            binding.recyclerView.layoutManager?.onRestoreInstanceState(it)
        }
        binding.recyclerView.adapter = adapter
        adapter.onItemClick = {
            Toast.makeText(requireContext(), it.name, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        binding?.recyclerView?.layoutManager?.onSaveInstanceState()?.let { state ->
            outState.putParcelable(rvStateKey, state)
        }
        super.onSaveInstanceState(outState)
    }

    private fun observeViewState() {
        viewModel.viewState
            .doOnNext(::handleViewState)
            .subscribe().addTo(compositeDisposable)
    }

    private fun handleViewState(state: SummaryViewState) {
        state.apply {
            handleViewStateError(viewStateError)
            adapter.submitList(summaries)
        }
    }


    companion object {
        private const val ARG_KEY_TITLE = "title"
        private const val ARG_KEY_CONTENT = "content"

        fun newInstance(title: SummaryTitle, item: Items): SummaryFragment {
            return SummaryFragment().apply {
                arguments = bundleOf(
                    ARG_KEY_TITLE to title,
                    ARG_KEY_CONTENT to item
                )
            }
        }
    }

}


sealed class SummaryTitle(val name: String) : Parcelable {
    @Parcelize
    object Comics : SummaryTitle("Comics")

    @Parcelize
    object Stories : SummaryTitle("Stories")

    @Parcelize
    object Series : SummaryTitle("Series")

}