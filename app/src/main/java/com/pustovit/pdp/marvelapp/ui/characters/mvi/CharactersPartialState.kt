package com.pustovit.pdp.marvelapp.ui.characters.mvi

import com.pustovit.pdp.marvelapp.domain.model.character.Character
import io.reactivex.functions.Function

object CharactersPartialState {

    fun query(query: String): Function<CharactersViewState, CharactersViewState> {
        return Function<CharactersViewState, CharactersViewState> { previousState ->
            previousState.copy(query = query)
        }
    }

    fun characters(characters: List<Character>): Function<CharactersViewState, CharactersViewState> {
        return Function<CharactersViewState, CharactersViewState> { previousState ->
            previousState.copy(characters = characters)
        }
    }

    fun loading(loading: Boolean): Function<CharactersViewState, CharactersViewState> {
        return Function<CharactersViewState, CharactersViewState> { previousState ->
            previousState.copy(loading = loading)
        }
    }

    fun error(error: Throwable?): Function<CharactersViewState, CharactersViewState> {
        return Function<CharactersViewState, CharactersViewState> { previousState ->
            previousState.copy(error = error)
        }
    }
}
