package com.pustovit.pdp.marvelapp.domain.model.common

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

/**
 * Created by Pustovit V.V.
 * Date: 31.05.2022
 * Time: 20:40
 */
@Parcelize
data class Thumbnail(
    val path: String = "",
    val extension: String = ""
) : Parcelable {

    @IgnoredOnParcel
    val url = "$path.$extension"
}