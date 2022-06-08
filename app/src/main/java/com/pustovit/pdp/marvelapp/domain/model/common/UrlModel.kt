package com.pustovit.pdp.marvelapp.domain.model.common

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UrlModel(
    val type: String = "",
    val url: String = ""
) : Parcelable