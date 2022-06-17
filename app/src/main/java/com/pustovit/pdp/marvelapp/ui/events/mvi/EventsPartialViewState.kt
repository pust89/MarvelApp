package com.pustovit.pdp.marvelapp.ui.events.mvi

import com.pustovit.pdp.marvelapp.domain.model.event.Event
import com.pustovit.pdp.marvelapp.common.mvi.PartialViewState

object EventsPartialViewState : PartialViewState<EventsViewState>() {

    fun events(events: List<Event>) = transform { previousState ->
        previousState.copy(events = events,
            loading = false,
            viewStateError = null)
    }

}

