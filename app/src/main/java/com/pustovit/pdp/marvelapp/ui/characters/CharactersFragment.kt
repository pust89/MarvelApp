package com.pustovit.pdp.marvelapp.ui.characters

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.ImageLoader
import com.pustovit.pdp.marvelapp.R
import com.pustovit.pdp.marvelapp.app.appComponent
import com.pustovit.pdp.marvelapp.common.android.hideKeyboard
import com.pustovit.pdp.marvelapp.common.delegate.CompositeDisposableDelegate
import com.pustovit.pdp.marvelapp.databinding.FragmentCharactersBinding
import com.pustovit.pdp.marvelapp.navigation.Screens
import com.pustovit.pdp.marvelapp.ui.characters.di.DaggerCharactersComponent
import com.pustovit.pdp.marvelapp.ui.characters.mvi.CharactersViewState
import com.pustovit.pdp.marvelapp.ui.extensions.router
import io.reactivex.BackpressureStrategy
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class CharactersFragment : Fragment(R.layout.fragment_characters) {

    @Inject
    lateinit var imageLoader: ImageLoader

    @Inject
    lateinit var viewModelFactory: CharactersViewModel.Factory

    private val viewModel by viewModels<CharactersViewModel> {
        viewModelFactory
    }

    private val charactersComponent by lazy {
        DaggerCharactersComponent.builder().appComponent(
            appComponent()
        ).build()
    }

    private val binding by viewBinding(FragmentCharactersBinding::bind)

    private val compositeDisposable by CompositeDisposableDelegate()

    private val adapter: CharactersListAdapter by lazy {
        CharactersListAdapter(
            imageLoader = imageLoader,
            onItemClick = {
                Toast.makeText(requireContext(), it.name, Toast.LENGTH_SHORT).show()
            }
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        charactersComponent.inject(this)
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
