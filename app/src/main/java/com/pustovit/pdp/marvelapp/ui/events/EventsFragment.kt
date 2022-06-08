package com.pustovit.pdp.marvelapp.ui.events

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.pustovit.pdp.marvelapp.R
import com.pustovit.pdp.marvelapp.app.appComponent
import com.pustovit.pdp.marvelapp.common.delegate.CompositeDisposableDelegate
import com.pustovit.pdp.marvelapp.databinding.FragmentEventsBinding
import com.pustovit.pdp.marvelapp.navigation.Screens
import com.pustovit.pdp.marvelapp.ui.characters.mvi.CharactersViewState
import com.pustovit.pdp.marvelapp.ui.common.extensions.router
import com.pustovit.pdp.marvelapp.ui.events.di.DaggerEventsComponent
import com.pustovit.pdp.marvelapp.ui.events.mvi.EventsViewState
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class EventsFragment : Fragment(R.layout.fragment_events) {
    @Inject
    lateinit var viewModelFactory: EventsViewModel.Factory

    @Inject
    lateinit var adapter: EventsListAdapter

    private val viewModel: EventsViewModel by viewModels { viewModelFactory }
    private val binding by viewBinding(FragmentEventsBinding::bind)
    private val compositeDisposable by CompositeDisposableDelegate()

    override fun onAttach(context: Context) {
        DaggerEventsComponent.builder()
            .appComponent(appComponent())
            .build()
            .inject(this@EventsFragment)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        viewModel.loadEvents()
        viewModel.viewState()
            .doOnNext(::handleViewState)
            .subscribe().addTo(compositeDisposable)
    }

    private fun initViews() {
        binding.recyclerView.adapter = adapter

        adapter.onItemClick = {
            viewModel.onEventClick(it)
        }

    }

    private fun handleViewState(state: EventsViewState) {
        state.apply {
            adapter.submitList(events)
        }
    }
}