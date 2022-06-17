package com.pustovit.pdp.marvelapp.ui.character.mvi

import com.pustovit.pdp.marvelapp.domain.model.character.Character
import com.pustovit.pdp.marvelapp.common.mvi.PartialViewState

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
