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
    val items: List<StorySummaryDto>?,
    @Json(name = "returned")
    val returned: Int?
)

@JsonClass(generateAdapter = true)
data class StorySummaryDto(
    @Json(name = "resourceURI")
    val resourceURI: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "type")
    val type: String?
)