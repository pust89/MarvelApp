package com.pustovit.pdp.marvelapp.ui.events.mvi

import com.pustovit.pdp.marvelapp.domain.model.event.Event
import com.pustovit.pdp.marvelapp.ui.common.mvi.PartialState

object EventsPartialState : PartialState<EventsViewState>() {

    fun events(events: List<Event>) = transform { previousState ->
        previousState.copy(events = events,
            loading = false,
            viewStateError = null)
    }

    fun loading(loading: Boolean) = transform { previousState ->
        previousState.copy(
            loading = loading,
            viewStateError = null
        )
    }

}

