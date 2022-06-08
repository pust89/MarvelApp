package com.pustovit.pdp.marvelapp.ui.character

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.pustovit.pdp.marvelapp.R
import com.pustovit.pdp.marvelapp.databinding.FragmentCharacterBinding
import com.pustovit.pdp.marvelapp.domain.model.character.Character
import com.pustovit.pdp.marvelapp.navigation.Screens
import com.pustovit.pdp.marvelapp.ui.extensions.router

class CharacterFragment : Fragment(R.layout.fragment_character) {

    private val binding by viewBinding(FragmentCharacterBinding::bind)

    private val viewModel: CharacterViewModel by lazy {
        ViewModelProvider(this)[CharacterViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textView.text = arguments?.getParcelable<Character>(ARG_KEY)?.name

        binding.button.setOnClickListener {
            router().navigateTo(Screens.comicsScreen())
        }
    }

    companion object {
        private const val ARG_KEY = "character"

        fun newInstance(character: Character): CharacterFragment {
            return CharacterFragment().apply {
                arguments = bundleOf(ARG_KEY to character)
            }
        }
    }
}