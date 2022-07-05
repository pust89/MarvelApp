package com.pustovit.pdp.common_ui.ui.mvi

interface Handleable {
    var needHandle: Boolean

    fun handle() {
        needHandle = false
    }
}