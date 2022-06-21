package com.pustovit.pdp.marvelapp.ui.character.summary.mvi

import com.pustovit.pdp.summary.model.SummaryItem
import com.pustovit.pdp.marvelapp.common.mvi.ViewState
import com.pustovit.pdp.marvelapp.common.mvi.ViewStateError

data class SummaryViewState(
    val summaries: List<SummaryItem> = emptyList<SummaryItem>(),
    override var viewStateError: ViewStateError? = null,
    override var loading: Boolean = false
) : ViewState
