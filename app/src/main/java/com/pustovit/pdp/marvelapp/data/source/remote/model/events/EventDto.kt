package com.pustovit.pdp.marvelapp.data.source.remote.model.events


import com.pustovit.pdp.marvelapp.data.source.remote.model.common.*
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EventDto(
    @Json(name = "characters")
    val characters: CharactersDto?,
    @Json(name = "comics")
    val comics: ComicsDto?,
    @Json(name = "creators")
    val creators: CreatorsDto?,
    @Json(name = "description")
    val description: String?,
    @Json(name = "end")
    val end: String?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "modified")
    val modified: String?,
    @Json(name = "next")
    val next: SummaryDto?,
    @Json(name = "previous")
    val previous: SummaryDto?,
    @Json(name = "resourceURI")
    val resourceURI: String?,
    @Json(name = "series")
    val series: SeriesDto?,
    @Json(name = "start")
    val start: String?,
    @Json(name = "stories")
    val stories: StoriesDto?,
    @Json(name = "thumbnail")
    val thumbnail: ThumbnailDto?,
    @Json(name = "title")
    val title: String?,
    @Json(name = "urls")
    val urls: List<UrlDto>?
)
