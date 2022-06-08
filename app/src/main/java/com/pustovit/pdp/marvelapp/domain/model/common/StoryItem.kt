package com.pustovit.pdp.marvelapp.domain.model.common

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class StoryItem(
    val resourceURI: String = "",
    val name: String = "",
    val type: String = ""
) : Parcelable

