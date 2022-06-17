package com.pustovit.pdp.marvelapp.ui.character.summary.mvi

import com.pustovit.pdp.marvelapp.ui.character.summary.model.SummaryItem
import com.pustovit.pdp.marvelapp.common.mvi.PartialViewState

object SummaryPartialViewState : PartialViewState<SummaryViewState>() {

    fun summaries(summaries: List<SummaryItem>) = transform { previousState ->
        previousState.copy(
            summaries = summaries
        )
    }
}