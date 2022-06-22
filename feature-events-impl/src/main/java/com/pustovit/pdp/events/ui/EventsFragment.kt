package com.pustovit.pdp.events.ui

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.pustoivt.pdp.events_api.model.Event
import com.pustovit.pdp.events.databinding.FragmentEventsBinding
import com.pustovit.pdp.events.di.ViewModelFactory
import com.pustovit.pdp.events.ui.mvi.EventsViewState
import com.pustovit.pdp.common_ui.ui.CompositeDisposableDelegate
import com.pustovit.pdp.common_ui.ui.baseViewModels
import com.pustovit.pdp.common_ui.ui.handleViewStateError
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class EventsFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var adapter: EventsListAdapter

    private val viewModel: EventsViewModel by baseViewModels { viewModelFactory }

    private var binding: FragmentEventsBinding? = null

    private val compositeDisposable by CompositeDisposableDelegate()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // TODO injection point 2
//        DaggerEventsComponent.builder()
//            .appComponent(appComponent())
//            .build()
//            .inject(this@EventsFragment)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = with(FragmentEventsBinding.inflate(layoutInflater)) {
        binding = this
        this.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.let {
            initViews(it, savedInstanceState)
        }
        viewModel.viewState
            .doOnNext(::handleViewState)
            .subscribe().addTo(compositeDisposable)
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    private fun initViews(binding: FragmentEventsBinding, savedInstanceState: Bundle?) {
        adapter.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY

        savedInstanceState?.getParcelable<Parcelable>(RV_STATE)?.let {
            binding.recyclerView.layoutManager?.onRestoreInstanceState(it)
        }
        binding.recyclerView.adapter = adapter

        adapter.onItemClick = (::onEventClick)

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.onRefresh()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        binding?.recyclerView?.layoutManager?.onSaveInstanceState()?.let { state ->
            outState.putParcelable(RV_STATE, state)
        }
        super.onSaveInstanceState(outState)
    }

    private fun handleViewState(state: EventsViewState) {
        state.apply {
            adapter.submitList(events)
            handleViewStateError(viewStateError)
        }

        binding?.let {

            val isLoading = state.loading
            val swrIsRefreshing = it.swipeRefreshLayout.isRefreshing

            if (isLoading && !swrIsRefreshing) {
                view?.post {
                    it.swipeRefreshLayout.isRefreshing = true
                }
            }
            if (!isLoading && swrIsRefreshing) {
                it.swipeRefreshLayout.isRefreshing = false
            }
        }

        if (binding?.swipeRefreshLayout?.isRefreshing == true) {
            binding?.swipeRefreshLayout?.isRefreshing = false
        }

    }

    private fun onEventClick(event: Event) {
        // TODO navigation point 2
//        router().navigateTo(Screens.eventScreen(event.id))
    }

    companion object {
        const val RV_STATE = "eventsRvState"
    }
}