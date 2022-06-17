package com.pustovit.pdp.marvelapp.common.mvi

interface Handleable {
    var needHandle: Boolean

    fun handle() {
        needHandle = false
    }
}