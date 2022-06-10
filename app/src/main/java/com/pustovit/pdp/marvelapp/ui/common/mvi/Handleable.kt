package com.pustovit.pdp.marvelapp.ui.common.mvi

interface Handleable {
    var needHandle: Boolean

    fun handle() {
        needHandle = false
    }
}