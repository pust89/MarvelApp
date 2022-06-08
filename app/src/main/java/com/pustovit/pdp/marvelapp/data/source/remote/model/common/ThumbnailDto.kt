package com.pustovit.pdp.marvelapp.data.source.remote.model.common

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ThumbnailDto(
    @Json(name = "path")
    val path: String?,
    @Json(name = "extension")
    val extension: String?
)