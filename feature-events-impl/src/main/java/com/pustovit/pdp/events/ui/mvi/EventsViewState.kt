package com.pustovit.pdp.events.ui.mvi

import com.pustoivt.pdp.events_api.model.Event
import com.pustovit.pdp.common_ui.ui.mvi.ViewState
import com.pustovit.pdp.common_ui.ui.mvi.ViewStateError

data class EventsViewState(
    val events: List<Event> = emptyList(),
    override var loading: Boolean = true,
    override var viewStateError: ViewStateError? = null
) : ViewState
