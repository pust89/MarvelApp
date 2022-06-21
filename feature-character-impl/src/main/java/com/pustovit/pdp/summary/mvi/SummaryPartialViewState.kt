package com.pustovit.pdp.summary.mvi

import com.pustovit.pdp.summary.model.SummaryItem
import com.pustovit.pdp.marvelapp.common.mvi.PartialViewState

object SummaryPartialViewState : PartialViewState<SummaryViewState>() {

    fun summaries(summaries: List<SummaryItem>) = transform { previousState ->
        previousState.copy(
            summaries = summaries
        )
    }
}