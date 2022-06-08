package com.pustovit.pdp.marvelapp.domain.model.common

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Item(
    val resourceURI: String = "",
    val name: String = ""
) : Parcelable