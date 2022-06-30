package com.pustovit.pdp.common_models.domain

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
data class Thumbnail(
    val path: String = "",
    val extension: String = ""
) : Parcelable {

    @IgnoredOnParcel
    val url = "$path.$extension"

    @IgnoredOnParcel
    val isValid = path.isNotEmpty() && path.isNotEmpty()
}