package com.pustovit.pdp.marvelapp.ui.character.mvi

import com.pustovit.pdp.marvelapp.domain.model.character.Character
import com.pustovit.pdp.marvelapp.ui.common.mvi.ViewState
import com.pustovit.pdp.marvelapp.ui.common.mvi.ViewStateError

data class CharacterViewState(
    val characterId: Int = 0,
    val character: Character = Character(),
    override var loading: Boolean = true,
    override var viewStateError: ViewStateError? = null
) : ViewState
