package com.pustovit.pdp.character.mvi

import com.pustovit.pdp.characters.api.model.Character
import com.pustovit.pdp.marvelapp.common.mvi.ViewState
import com.pustovit.pdp.marvelapp.common.mvi.ViewStateError

data class CharacterViewState(
    val character: Character = Character(),
    val comicsCount: Int = 0,
    val seriesCount: Int = 0,
    val storiesCount: Int = 0,
    override var loading: Boolean = true,
    override var viewStateError: ViewStateError? = null
) : ViewState
