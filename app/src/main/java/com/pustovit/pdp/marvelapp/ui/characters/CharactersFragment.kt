package com.pustovit.pdp.marvelapp.ui.characters

import android.content.Context
import android.os.Bundle
import android.view.View
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
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()

        viewModel.loadCharacters()

        viewModel.viewState()
            .doOnNext(::handleViewState)
            .subscribe().addTo(compositeDisposable)
    }

    private fun initViews() {
        binding.recyclerView.adapter = adapter

        adapter.onItemClick = {
            viewModel.onCharacterClick(it)
        }

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

        binding.searchView.setOnCloseListener {
            viewModel.clearSearchResult()
            true
        }
    }

    private fun handleViewState(state: CharactersViewState) {
        state.apply {
            adapter.submitList(characters)
        }
    }
}
