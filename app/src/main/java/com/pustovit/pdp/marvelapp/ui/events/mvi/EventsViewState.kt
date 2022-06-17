package com.pustovit.pdp.marvelapp.ui.events.mvi

import com.pustovit.pdp.marvelapp.domain.model.event.Event
import com.pustovit.pdp.marvelapp.common.mvi.ViewState
import com.pustovit.pdp.marvelapp.common.mvi.ViewStateError

data class EventsViewState(
    val events: List<Event> = emptyList(),
    override var loading: Boolean = true,
    override var viewStateError: ViewStateError? = null
) : ViewState
