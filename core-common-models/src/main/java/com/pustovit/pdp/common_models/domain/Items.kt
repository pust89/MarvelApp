package com.pustovit.pdp.common_models.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Items(
    val available: Int = 0,
    val collectionURI: String = "",
    val items: List<Summary> = emptyList(),
    val returned: Int = 0
) : Parcelable