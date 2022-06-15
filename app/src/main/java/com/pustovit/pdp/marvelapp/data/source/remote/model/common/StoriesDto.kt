package com.pustovit.pdp.marvelapp.data.source.remote.model.common

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StoriesDto(
    @Json(name = "available")
    val available: Int?,
    @Json(name = "collectionURI")
    val collectionURI: String?,
    @Json(name = "items")
    val items: List<SummaryDto>?,
    @Json(name = "returned")
    val returned: Int?
)