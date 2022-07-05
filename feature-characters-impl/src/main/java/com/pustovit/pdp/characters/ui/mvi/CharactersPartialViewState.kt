package com.pustovit.pdp.characters.ui.mvi

import com.pustovit.pdp.characters_api.api.model.Character
import com.pustovit.pdp.common_ui.ui.mvi.PartialViewState

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
