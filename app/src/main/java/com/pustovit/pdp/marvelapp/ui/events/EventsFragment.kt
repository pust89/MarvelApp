package com.pustovit.pdp.marvelapp.ui.events

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.pustovit.pdp.marvelapp.app.appComponent
import com.pustovit.pdp.marvelapp.common.delegate.CompositeDisposableDelegate
import com.pustovit.pdp.marvelapp.databinding.FragmentEventsBinding
import com.pustovit.pdp.marvelapp.ui.events.di.DaggerEventsComponent
import com.pustovit.pdp.marvelapp.ui.events.di.ViewModelFactory
import com.pustovit.pdp.marvelapp.ui.events.mvi.EventsViewState
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class EventsFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var adapter: EventsListAdapter

    private val viewModel: EventsViewModel by viewModels { viewModelFactory }

    private var binding: FragmentEventsBinding? = null

    private val compositeDisposable by CompositeDisposableDelegate()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerEventsComponent.builder()
            .appComponent(appComponent())
            .build()
            .inject(this@EventsFragment)
        viewModel.onAttach()
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

    private fun initViews(binding: FragmentEventsBinding, savedInstanceState: Bundle?) {
        adapter.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY

        savedInstanceState?.getParcelable<Parcelable>(RV_STATE)?.let {
            binding.recyclerView.layoutManager?.onRestoreInstanceState(it)
        }
        binding.recyclerView.adapter = adapter

        adapter.onItemClick = {
            viewModel.onEventClick(it)
        }
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
        adapter.submitList(state.events)

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

        state.viewStateError?.let {
            if (it.needHandle) {
                it.handle()
                Toast.makeText(requireContext(), it.error.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        const val RV_STATE = "eventsRvState"
    }
}