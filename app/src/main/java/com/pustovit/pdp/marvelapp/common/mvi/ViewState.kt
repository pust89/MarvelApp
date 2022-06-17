package com.pustovit.pdp.marvelapp.common.mvi

interface ViewState {
    var viewStateError: ViewStateError?
    var loading: Boolean
}