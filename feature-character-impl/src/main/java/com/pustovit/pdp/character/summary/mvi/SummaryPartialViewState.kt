package com.pustovit.pdp.character.summary.mvi

import com.pustovit.pdp.character.summary.model.SummaryItem
import com.pustovit.pdp.common_ui.ui.mvi.PartialViewState

object SummaryPartialViewState : PartialViewState<SummaryViewState>() {

    fun summaries(summaries: List<SummaryItem>) = transform { previousState ->
        previousState.copy(
            summaries = summaries
        )
    }
}