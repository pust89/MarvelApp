package com.pustovit.pdp.common_models.response

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class MarvelResponse<T>(
    @Json(name = "code")
    val code: Int?,
    @Json(name = "status")
    val status: String?,
    @Json(name = "copyright")
    val copyright: String?,
    @Json(name = "attributionText")
    val attributionText: String?,
    @Json(name = "attributionHTML")
    val attributionHTML: String?,
    @Json(name = "etag")
    val etag: String?,
    @Json(name = "data")
    val data: Data<T>?
) {
    @Keep
    @JsonClass(generateAdapter = true)
    data class Data<T>(
        @Json(name = "offset")
        val offset: Int?,
        @Json(name = "limit")
        val limit: Int?,
        @Json(name = "total")
        val total: Int?,
        @Json(name = "count")
        val count: Int?,
        @Json(name = "results")
        val results: List<T>?
    )
}
