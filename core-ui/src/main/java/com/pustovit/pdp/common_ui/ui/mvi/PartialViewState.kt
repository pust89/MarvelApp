package com.pustovit.pdp.common_ui.ui.mvi

import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function

abstract class PartialViewState<VS : ViewState> {

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

    companion object {
        fun <VS : ViewState> apply(): BiFunction<VS, Function<VS, VS>, VS> {
            return BiFunction { state, partial ->
                partial.apply(state)
            }
        }
    }
}