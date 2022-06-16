package com.pustovit.pdp.marvelapp.ui.event.mvi

import com.pustovit.pdp.marvelapp.domain.model.character.Character
import com.pustovit.pdp.marvelapp.domain.model.event.Event
import com.pustovit.pdp.marvelapp.ui.common.mvi.PartialState

object EventPartialState : PartialState<EventViewState>() {

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
