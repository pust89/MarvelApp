package com.pustovit.pdp.marvelapp.domain.model.common

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Characters(
    val available: Int = 0,
    val collectionURI: String = "",
    val items: List<Item> = emptyList(),
    val returned: Int = 0
) : Parcelable