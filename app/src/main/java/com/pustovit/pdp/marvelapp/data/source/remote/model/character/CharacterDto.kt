package com.pustovit.pdp.marvelapp.data.source.remote.model.character


import com.pustovit.pdp.marvelapp.data.source.remote.model.common.*
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacterDto(
    @Json(name = "id")
    val id: Int?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "description")
    val description: String?,
    @Json(name = "modified")
    val modified: String?,
    @Json(name = "thumbnail")
    val thumbnail: ThumbnailDto?,
    @Json(name = "resourceURI")
    val resourceURI: String?,
    @Json(name = "comics")
    val comics: ItemsDto?,
    @Json(name = "series")
    val series: ItemsDto?,
    @Json(name = "stories")
    val stories: ItemsDto?,
    @Json(name = "events")
    val events: ItemsDto?,
    @Json(name = "urls")
    val urls: List<UrlDto>?
)

