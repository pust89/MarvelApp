package com.pustovit.pdp.marvelapp.domain.model

import android.os.Parcelable
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
) : Parcelable

@Parcelize
data class UrlModel(
    val type: String = "",
    val url: String = ""
) : Parcelable

@Parcelize
data class Item(
    val resourceURI: String = "",
    val name: String = ""
) : Parcelable

@Parcelize
data class StoryItem(
    val resourceURI: String = "",
    val name: String = "",
    val type: String = ""
) : Parcelable

