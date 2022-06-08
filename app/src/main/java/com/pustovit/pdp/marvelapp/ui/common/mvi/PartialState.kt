package com.pustovit.pdp.marvelapp.ui.common.mvi

import io.reactivex.functions.Function

abstract class PartialState<ViewState> {

    fun transform(transformation: (previousState: ViewState) -> ViewState): Function<ViewState, ViewState> {
        return Function<ViewState, ViewState> { previousState ->
            transformation(previousState)
        }
    }
}