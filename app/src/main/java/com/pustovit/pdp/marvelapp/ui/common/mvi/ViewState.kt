package com.pustovit.pdp.marvelapp.ui.common.mvi

interface ViewState {
    var viewStateError: ViewStateError?
    var loading: Boolean
}