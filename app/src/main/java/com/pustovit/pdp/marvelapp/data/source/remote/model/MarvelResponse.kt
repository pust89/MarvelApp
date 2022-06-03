package com.pustovit.pdp.marvelapp.data.source.remote.model

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by Pustovit V.V.
 * Date: 31.05.2022
 * Time: 19:14
 */
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
