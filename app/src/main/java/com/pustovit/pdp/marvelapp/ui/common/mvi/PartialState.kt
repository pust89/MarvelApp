package com.pustovit.pdp.marvelapp.ui.common.mvi

import io.reactivex.functions.Function

abstract class PartialState<VS> where VS : ViewState {

    fun transform(transformation: (previousState: VS) -> VS): Function<VS, VS> {
        return Function<VS, VS> { previousState ->
            transformation(previousState)
        }
    }

    open fun loading(loading: Boolean): Function<VS, VS> {
        return transform { vs ->
            vs.loading = loading
            vs.viewStateError = null
            vs
        }
    }
}