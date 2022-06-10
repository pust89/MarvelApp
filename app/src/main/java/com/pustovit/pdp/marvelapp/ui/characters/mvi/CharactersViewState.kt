package com.pustovit.pdp.marvelapp.ui.characters.mvi

import com.pustovit.pdp.marvelapp.domain.model.character.Character
import com.pustovit.pdp.marvelapp.ui.common.mvi.ViewState
import com.pustovit.pdp.marvelapp.ui.common.mvi.ViewStateError

data class CharactersViewState(
    val query: String = "",
    val characters: List<Character> = emptyList(),
    override var loading: Boolean = false,
    override var viewStateError: ViewStateError? = null
): ViewState
