package com.pustovit.pdp.marvelapp.ui.characters

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.pustovit.pdp.marvelapp.R
import com.pustovit.pdp.marvelapp.app.appComponent
import com.pustovit.pdp.marvelapp.databinding.FragmentCharactersBinding
import com.pustovit.pdp.marvelapp.navigation.Screens
import com.pustovit.pdp.marvelapp.ui.characters.di.DaggerCharactersComponent
import com.pustovit.pdp.marvelapp.ui.extensions.router
import javax.inject.Inject

class CharactersFragment : Fragment(R.layout.fragment_characters) {

    private val binding by viewBinding(FragmentCharactersBinding::bind)

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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        charactersComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadCharacters()
        binding.button.setOnClickListener {
            router().navigateTo(Screens.characterScreen())
        }
    }

}
