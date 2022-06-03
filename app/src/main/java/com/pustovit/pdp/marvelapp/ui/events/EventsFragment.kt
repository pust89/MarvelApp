package com.pustovit.pdp.marvelapp.ui.events

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.pustovit.pdp.marvelapp.R
import com.pustovit.pdp.marvelapp.databinding.FragmentEventsBinding
import com.pustovit.pdp.marvelapp.navigation.Screens
import com.pustovit.pdp.marvelapp.ui.extensions.router

class EventsFragment : Fragment(R.layout.fragment_events) {
    private val binding by viewBinding(FragmentEventsBinding::bind)

    private val viewModel: EventsViewModel by lazy {
        ViewModelProvider(this)[EventsViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.text.observe(viewLifecycleOwner) {
            binding.textView.text = it
        }

        binding.button.setOnClickListener {
            router().navigateTo(Screens.eventScreen())
        }
    }
}