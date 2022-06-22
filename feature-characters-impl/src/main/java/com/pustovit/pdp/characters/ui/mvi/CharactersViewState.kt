package com.pustovit.pdp.characters.ui.mvi

import com.pustovit.pdp.characters_api.api.model.Character
import com.pustovit.pdp.common_ui.ui.mvi.ViewState
import com.pustovit.pdp.common_ui.ui.mvi.ViewStateError

data class CharactersViewState(
    val query: String = "",
    val characters: List<Character> = emptyList(),
    override var loading: Boolean = true,
    override var viewStateError: ViewStateError? = null
) : ViewState
