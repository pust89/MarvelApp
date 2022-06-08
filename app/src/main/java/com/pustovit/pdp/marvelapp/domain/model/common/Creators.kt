package com.pustovit.pdp.marvelapp.domain.model.common

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Creators(
    val available: Int = 0,
    val collectionURI: String = "",
    val items: List<Summary> = emptyList(),
    val returned: Int = 0
): Parcelable