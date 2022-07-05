package com.pustovit.pdp.events.ui.mvi

import com.pustoivt.pdp.events_api.model.Event
import com.pustovit.pdp.common_ui.ui.mvi.PartialViewState

object EventsPartialViewState : PartialViewState<EventsViewState>() {

    fun events(events: List<Event>) = transform { previousState ->
        previousState.copy(events = events,
            loading = false,
            viewStateError = null)
    }

}

