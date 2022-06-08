package com.pustovit.pdp.marvelapp.ui.comics

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.pustovit.pdp.marvelapp.R
import com.pustovit.pdp.marvelapp.databinding.FragmentCharactersBinding
import com.pustovit.pdp.marvelapp.databinding.FragmentComicsBinding
import com.pustovit.pdp.marvelapp.navigation.Screens
import com.pustovit.pdp.marvelapp.ui.common.extensions.router

class ComicsFragment : Fragment(R.layout.fragment_comics) {

    private val binding by viewBinding(FragmentComicsBinding::bind)

    private val viewModel: ComicsViewModel by lazy {
        ViewModelProvider(this)[ComicsViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.text.observe(viewLifecycleOwner) {
            binding.textView.text = it
        }
    }
}