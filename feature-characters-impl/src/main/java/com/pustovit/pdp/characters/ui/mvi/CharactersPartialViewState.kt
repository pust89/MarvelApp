package com.pustovit.pdp.characters.ui.mvi

import com.pustovit.pdp.characters.api.model.Character
import com.pustovit.pdp.marvelapp.common.mvi.PartialViewState

object CharactersPartialViewState : PartialViewState<CharactersViewState>() {

    fun query(query: String) = transform { previousState ->
        previousState.copy(
            loading = true,
            viewStateError = null,
            query = query
        )
    }

    fun characters(characters: List<Character>) = transform { previousState ->
        previousState.copy(
            loading = false,
            viewStateError = null,
            characters = characters
        )
    }

}
