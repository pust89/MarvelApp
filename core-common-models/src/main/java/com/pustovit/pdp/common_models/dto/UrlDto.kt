package com.pustovit.pdp.common_models.dto

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class UrlDto(
    @Json(name = "type")
    val type: String?,
    @Json(name = "url")
    val url: String?
)