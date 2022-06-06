package com.pustovit.pdp.marvelapp.ui.characters.mvi

import com.pustovit.pdp.marvelapp.domain.model.characters.Character

data class CharactersViewState(
    val query: String = "",
    val characters: List<Character> = emptyList(),
    val loading: Boolean = false,
    val error: Throwable? = null
)
