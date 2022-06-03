package com.pustovit.pdp.marvelapp.ui.characters

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.pustovit.pdp.marvelapp.R
import com.pustovit.pdp.marvelapp.app.appComponent
import com.pustovit.pdp.marvelapp.databinding.FragmentCharactersBinding
import com.pustovit.pdp.marvelapp.navigation.Screens
import com.pustovit.pdp.marvelapp.ui.extensions.router
import javax.inject.Inject

class CharactersFragment : Fragment(R.layout.fragment_characters) {

    private val binding by viewBinding(FragmentCharactersBinding::bind)

    @Inject
    lateinit var viewModel: CharactersViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        appComponent().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
                binding.button.setOnClickListener {
                    viewModel.loadCharacters()
            //router().navigateTo(Screens.characterScreen())
        }

    }

}

    //    private val viewModel: CharactersViewModel by lazy {
//        ViewModelProvider(this)[CharactersViewModel::class.java]
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        viewModel.text.observe(viewLifecycleOwner) {
//            binding.textView.text = it
//        }
//
//        binding.button.setOnClickListener {
//            router().navigateTo(Screens.characterScreen())
//        }
//    }
