package com.pustovit.pdp.character.mvi

import com.pustovit.pdp.characters_api.api.model.Character
import com.pustovit.pdp.common_ui.ui.mvi.PartialViewState

object CharacterPartialViewState : PartialViewState<CharacterViewState>() {

    fun character(character: Character) = transform { previousState ->
        previousState.copy(
            loading = false,
            viewStateError = null,
            character = character,
            comicsCount = character.comics.available,
            storiesCount = character.stories.available,
            seriesCount = character.series.available
            )
    }

}
