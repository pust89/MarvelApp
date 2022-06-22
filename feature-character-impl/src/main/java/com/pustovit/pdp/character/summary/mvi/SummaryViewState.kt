package com.pustovit.pdp.character.summary.mvi

import com.pustovit.pdp.character.summary.model.SummaryItem
import com.pustovit.pdp.common_ui.ui.mvi.ViewState
import com.pustovit.pdp.common_ui.ui.mvi.ViewStateError

data class SummaryViewState(
    val summaries: List<SummaryItem> = emptyList<SummaryItem>(),
    override var viewStateError: ViewStateError? = null,
    override var loading: Boolean = false
) : ViewState
