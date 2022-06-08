package com.pustovit.pdp.marvelapp.data.source.remote.model.common

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ItemDto(
    @Json(name = "resourceURI")
    val resourceURI: String?,
    @Json(name = "name")
    val name: String?
)