package com.pustovit.pdp.marvelapp.ui.characters

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.pustovit.pdp.marvelapp.app.appComponent
import com.pustovit.pdp.marvelapp.common.android.hideKeyboard
import com.pustovit.pdp.marvelapp.common.delegate.CompositeDisposableDelegate
import com.pustovit.pdp.marvelapp.databinding.FragmentCharactersBinding
import com.pustovit.pdp.marvelapp.ui.characters.di.DaggerCharactersComponent
import com.pustovit.pdp.marvelapp.ui.characters.di.ViewModelFactory
import com.pustovit.pdp.marvelapp.ui.characters.mvi.CharactersViewState
import com.pustovit.pdp.marvelapp.ui.common.extensions.handleViewStateError
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class CharactersFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var adapter: CharactersListAdapter

    private val viewModel by viewModels<CharactersViewModel> {
        viewModelFactory
    }

    private var binding: FragmentCharactersBinding? = null

    private val compositeDisposable by CompositeDisposableDelegate()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerCharactersComponent.builder()
            .appComponent(appComponent())
            .build().inject(this)
        viewModel.onAttach()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = with(FragmentCharactersBinding.inflate(layoutInflater)) {
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
        observeViewState()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        binding?.recyclerView?.layoutManager?.onSaveInstanceState()?.let { state ->
            outState.putParcelable(RV_STATE, state)
        }
        super.onSaveInstanceState(outState)
    }

    private fun initViews(binding: FragmentCharactersBinding, savedInstanceState: Bundle?) {
        adapter.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY

        savedInstanceState?.getParcelable<Parcelable>(RV_STATE)?.let {
            binding.recyclerView.layoutManager?.onRestoreInstanceState(it)
        }
        binding.recyclerView.adapter = adapter
        adapter.onItemClick = {
            viewModel.onCharacterClick(it)
        }
        setSearchView(binding)
    }

    private fun setSearchView(binding: FragmentCharactersBinding) {
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
        state.apply {
            adapter.submitList(characters)
            binding?.progressBar?.visibility = if (loading) View.VISIBLE else View.GONE
            handleViewStateError(viewStateError)
        }
    }

    companion object {
        const val RV_STATE = "charactersRvState"
    }
}
