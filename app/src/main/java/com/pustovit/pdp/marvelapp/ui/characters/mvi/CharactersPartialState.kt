package com.pustovit.pdp.marvelapp.ui.characters.mvi

import com.pustovit.pdp.marvelapp.domain.model.character.Character
import com.pustovit.pdp.marvelapp.ui.common.mvi.PartialState

object CharactersPartialState : PartialState<CharactersViewState>() {

    fun query(query: String) = transform { previousState ->
        previousState.copy(query = query)
    }

    fun characters(characters: List<Character>) = transform { previousState ->
        previousState.copy(characters = characters)
    }

    fun loading(loading: Boolean) = transform { previousState ->
        previousState.copy(loading = loading)
    }

    fun error(error: Throwable?) = transform { previousState ->
        previousState.copy(error = error)
    }

}
