package com.pustovit.pdp.event.mvi

import com.pustoivt.pdp.events.api.model.Event
import com.pustovit.pdp.characters.api.model.Character
import com.pustovit.pdp.marvelapp.common.mvi.PartialViewState

object EventPartialViewState : PartialViewState<EventViewState>() {

    fun event(event: Event) = transform { previousState ->
        previousState.copy(
            loading = false,
            viewStateError = null,
            event = event
        )
    }

    fun characters(characters: List<Character>) = transform { previousState ->
        previousState.copy(
            characters = characters
        )
    }

}
