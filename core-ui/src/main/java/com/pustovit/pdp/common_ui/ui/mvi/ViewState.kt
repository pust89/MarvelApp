package com.pustovit.pdp.common_ui.ui.mvi

import com.pustovit.pdp.common_ui.ui.mvi.ViewStateError

interface ViewState {
    var viewStateError: ViewStateError?
    var loading: Boolean
}