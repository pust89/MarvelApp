package com.pustovit.pdp.marvelapp.ui.events.mvi

import com.pustovit.pdp.marvelapp.domain.model.event.Event

data class EventsViewState(
    val events: List<Event> = emptyList(),
    val loading: Boolean = false,
    val error: Throwable? = null
)
