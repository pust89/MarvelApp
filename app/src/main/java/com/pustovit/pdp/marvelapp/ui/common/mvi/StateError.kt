package com.pustovit.pdp.marvelapp.ui.common.mvi

class ViewStateError(
    val error: Throwable,
    override var needHandle: Boolean = true
) : Handleable