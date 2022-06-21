package com.pustovit.pdp.event.mvi

import com.pustoivt.pdp.events.api.model.Event
import com.pustovit.pdp.characters.api.model.Character
import com.pustovit.pdp.marvelapp.common.mvi.ViewState
import com.pustovit.pdp.marvelapp.common.mvi.ViewStateError

data class EventViewState(
    val event: Event = Event(),
    val characters: List<Character> = emptyList(),
    override var loading: Boolean = true,
    override var viewStateError: ViewStateError? = null
) : ViewState