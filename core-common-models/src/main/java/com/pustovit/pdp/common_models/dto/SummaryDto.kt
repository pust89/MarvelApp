package com.pustovit.pdp.common_models.dto

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class SummaryDto(
    @Json(name = "resourceURI")
    val resourceURI: String?,
    @Json(name = "name")
    val name: String?
)