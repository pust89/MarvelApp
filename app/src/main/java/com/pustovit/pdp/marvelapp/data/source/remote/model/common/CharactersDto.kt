package com.pustovit.pdp.marvelapp.data.source.remote.model.common

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharactersDto(
    @Json(name = "available")
    val available: Int?,
    @Json(name = "collectionURI")
    val collectionURI: String?,
    @Json(name = "items")
    val items: List<CharacterSummaryDto>?,
    @Json(name = "returned")
    val returned: Int?
)

@JsonClass(generateAdapter = true)
data class CharacterSummaryDto(
    @Json(name = "resourceURI")
    val resourceURI: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "role")
    val role: String?
)