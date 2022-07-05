package com.pustovit.pdp.common_models.dto

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class ThumbnailDto(
    @Json(name = "path")
    val path: String?,
    @Json(name = "extension")
    val extension: String?
)