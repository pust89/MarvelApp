package com.pustovit.pdp.marvelapp.domain.model.common

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Characters(
    val available: Int = 0,
    val collectionURI: String = "",
    val items: List<CharacterSummary> = emptyList(),
    val returned: Int = 0
) : Parcelable

@Parcelize
data class CharacterSummary(
    val resourceURI: String = "",
    val name: String = "",
    val role: String = ""
) : Parcelable