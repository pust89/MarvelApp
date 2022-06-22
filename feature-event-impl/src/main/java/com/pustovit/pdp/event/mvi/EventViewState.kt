package com.pustovit.pdp.event.mvi

import com.pustoivt.pdp.events_api.model.Event
import com.pustovit.pdp.characters_api.api.model.Character
import com.pustovit.pdp.common_ui.ui.mvi.ViewState
import com.pustovit.pdp.common_ui.ui.mvi.ViewStateError

data class EventViewState(
    val event: Event = Event(),
    val characters: List<Character> = emptyList(),
    override var loading: Boolean = true,
    override var viewStateError: ViewStateError? = null
) : ViewState