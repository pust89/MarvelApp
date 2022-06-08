package com.pustovit.pdp.marvelapp.data.source.remote.model.common

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UrlDto(
    @Json(name = "type")
    val type: String?,
    @Json(name = "url")
    val url: String?
)