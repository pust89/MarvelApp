package com.pustovit.pdp.marvelapp.ui.event

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.pustovit.pdp.marvelapp.R
import com.pustovit.pdp.marvelapp.databinding.FragmentEventBinding
import com.pustovit.pdp.marvelapp.ui.events.EventsViewModel

/**
 * Created by Pustovit V.V.
 * Date: 30.05.2022
 * Time: 19:59
 */
class EventFragment : Fragment(R.layout.fragment_event) {
    private val binding by viewBinding(FragmentEventBinding::bind)

    private val viewModel: EventsViewModel by lazy {
        ViewModelProvider(this)[EventsViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.text.observe(viewLifecycleOwner) {
            binding.textView.text = it
        }
    }
}