package com.pustovit.pdp.common_ui.ui.mvi

import com.pustovit.pdp.common_ui.ui.mvi.Handleable

class ViewStateError(
    val error: Throwable,
    override var needHandle: Boolean = true
) : Handleable