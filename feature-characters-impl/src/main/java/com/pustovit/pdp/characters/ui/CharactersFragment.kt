package com.pustovit.pdp.characters.ui

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pustivut.pdp.core_navigation.router
import com.pustivut.pdp.core_navigation.screens
import com.pustovit.pdp.characters.databinding.FragmentCharactersBinding
import com.pustovit.pdp.characters.di.CharactersComponentHolder
import com.pustovit.pdp.characters_api.api.adapter.CharactersListAdapter
import com.pustovit.pdp.characters_api.api.model.Character
import com.pustovit.pdp.characters.di.ViewModelFactory
import com.pustovit.pdp.characters.ui.mvi.CharactersViewState
import com.pustovit.pdp.common_ui.ui.*
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class CharactersFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var adapter: CharactersListAdapter

    private val viewModel by baseViewModels<CharactersViewModel> {
        viewModelFactory
    }

    private var binding: FragmentCharactersBinding? = null

    private val compositeDisposable by CompositeDisposableDelegate()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        CharactersComponentHolder.getComponent().inject(this)
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
        val gridLm = GridLayoutManager(requireContext(), 2)
        binding.recyclerView.layoutManager = gridLm

        savedInstanceState?.getParcelable<Parcelable>(RV_STATE)?.let { state ->
            gridLm.onRestoreInstanceState(state)
        }

        binding.recyclerView.adapter = adapter
        adapter.onItemClick = (::onCharacterClick)
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

    private fun onCharacterClick(character: Character) {
        router.navigateTo(
            screens.characterScreen(characterId = character.id)
        )
    }

    companion object {
        private const val RV_STATE = "charactersRvState"
    }
}
