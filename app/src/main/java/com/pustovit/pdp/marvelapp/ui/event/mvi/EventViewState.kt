package com.pustovit.pdp.marvelapp.ui.event.mvi

import com.pustovit.pdp.marvelapp.domain.model.event.Event
import com.pustovit.pdp.marvelapp.ui.common.mvi.ViewState
import com.pustovit.pdp.marvelapp.ui.common.mvi.ViewStateError

data class EventViewState(
    val event: Event = Event(),
    override var loading: Boolean = true,
    override var viewStateError: ViewStateError? = null
) : ViewState