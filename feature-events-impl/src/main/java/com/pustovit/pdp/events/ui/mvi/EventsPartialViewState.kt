package com.pustovit.pdp.events.ui.mvi

import com.pustoivt.pdp.events.api.model.Event
import com.pustovit.pdp.marvelapp.common.mvi.PartialViewState

object EventsPartialViewState : PartialViewState<EventsViewState>() {

    fun events(events: List<Event>) = transform { previousState ->
        previousState.copy(events = events,
            loading = false,
            viewStateError = null)
    }

}

