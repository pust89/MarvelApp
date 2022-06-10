package com.pustovit.pdp.marvelapp.ui.characters

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.pustovit.pdp.marvelapp.R
import com.pustovit.pdp.marvelapp.app.appComponent
import com.pustovit.pdp.marvelapp.common.android.hideKeyboard
import com.pustovit.pdp.marvelapp.common.delegate.CompositeDisposableDelegate
import com.pustovit.pdp.marvelapp.databinding.FragmentCharactersBinding
import com.pustovit.pdp.marvelapp.ui.characters.di.DaggerCharactersComponent
import com.pustovit.pdp.marvelapp.ui.characters.mvi.CharactersViewState
import io.reactivex.rxkotlin.addTo
import timber.log.Timber
import javax.inject.Inject

class CharactersFragment : Fragment(R.layout.fragment_characters) {
    @Inject
    lateinit var viewModelFactory: CharactersViewModel.Factory

    @Inject
    lateinit var adapter: CharactersListAdapter

    private val viewModel by viewModels<CharactersViewModel> { viewModelFactory }
    private val binding by viewBinding(FragmentCharactersBinding::bind)
    private val compositeDisposable by CompositeDisposableDelegate()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerCharactersComponent.builder()
            .appComponent(appComponent())
            .build().inject(this)
        viewModel.onAttach()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observeViewState()
    }

    private fun initViews() {
        binding.recyclerView.adapter = adapter
        adapter.onItemClick = {
            viewModel.onCharacterClick(it)
        }
        setSearchView()
    }

    private fun setSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.handleUserInput(query)
                hideKeyboard()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.handleUserInput(newText)
                return true
            }
        })
    }

    private fun observeViewState() {
        viewModel.viewState
            .doOnNext(::handleViewState)
            .subscribe().addTo(compositeDisposable)
    }

    private fun handleViewState(state: CharactersViewState) {
        Timber.d("handleViewState $state")
        Timber.d("handleViewState error ${state.viewStateError?.error}")

        adapter.submitList(state.characters)
        binding.progressBar.visibility = if (state.loading) View.VISIBLE else View.GONE

        state.viewStateError?.let {
            if (it.needHandle) {
                it.handle()
                Toast.makeText(requireContext(), it.error.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
