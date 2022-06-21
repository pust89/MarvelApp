package com.pustovit.pdp.characters.ui.mvi

import com.pustovit.pdp.characters.domain.model.Character
import com.pustovit.pdp.marvelapp.common.mvi.ViewState
import com.pustovit.pdp.marvelapp.common.mvi.ViewStateError

data class CharactersViewState(
    val query: String = "",
    val characters: List<Character> = emptyList(),
    override var loading: Boolean = true,
    override var viewStateError: ViewStateError? = null
) : ViewState
