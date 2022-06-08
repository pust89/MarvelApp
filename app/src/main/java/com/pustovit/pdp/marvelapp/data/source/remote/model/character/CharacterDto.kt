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
    val comics: ComicsDto?,
    @Json(name = "series")
    val series: SeriesDto?,
    @Json(name = "stories")
    val stories: StoriesDto?,
    @Json(name = "events")
    val events: EventsDto?,
    @Json(name = "urls")
    val urls: List<UrlDto>?
)

