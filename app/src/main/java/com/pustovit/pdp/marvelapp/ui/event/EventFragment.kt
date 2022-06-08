package com.pustovit.pdp.marvelapp.ui.event

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.pustovit.pdp.marvelapp.R
import com.pustovit.pdp.marvelapp.app.appComponent
import com.pustovit.pdp.marvelapp.databinding.FragmentEventBinding
import com.pustovit.pdp.marvelapp.domain.model.event.Event
import com.pustovit.pdp.marvelapp.ui.events.EventsViewModel
import com.pustovit.pdp.marvelapp.ui.events.di.DaggerEventsComponent

class EventFragment : Fragment(R.layout.fragment_event) {

    private val binding by viewBinding(FragmentEventBinding::bind)

    private val viewModel: EventsViewModel by lazy {
        ViewModelProvider(this)[EventsViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textView.text = arguments?.getParcelable<Event>(ARG_KEY)?.title

    }

    companion object {
        private const val ARG_KEY = "event"

        fun newInstance(event: Event): EventFragment {
            return EventFragment().apply {
                arguments = bundleOf(ARG_KEY to event)
            }
        }
    }
}