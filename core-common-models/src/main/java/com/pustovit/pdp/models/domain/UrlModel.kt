package com.pustovit.pdp.models.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UrlModel(
    val type: String = "",
    val url: String = ""
) : Parcelable