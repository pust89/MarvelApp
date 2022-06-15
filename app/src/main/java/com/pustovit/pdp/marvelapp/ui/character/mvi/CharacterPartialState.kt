package com.pustovit.pdp.marvelapp.ui.character.mvi

import com.pustovit.pdp.marvelapp.domain.model.character.Character
import com.pustovit.pdp.marvelapp.ui.common.mvi.PartialState

object CharacterPartialState : PartialState<CharacterViewState>() {

    fun characterId(characterId: Int) = transform { previousState ->
        previousState.copy(
            loading = true,
            viewStateError = null,
            characterId = characterId
        )
    }

    fun character(character: Character) = transform { previousState ->
        previousState.copy(
            loading = false,
            viewStateError = null,
            character = character
        )
    }

}