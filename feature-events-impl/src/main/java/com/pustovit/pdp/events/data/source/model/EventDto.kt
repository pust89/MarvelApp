package com.pustovit.pdp.events.data.source.model

import com.pustovit.pdp.models.dto.ItemsDto
import com.pustovit.pdp.models.dto.SummaryDto
import com.pustovit.pdp.models.dto.ThumbnailDto
import com.pustovit.pdp.models.dto.UrlDto
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EventDto(
    @Json(name = "characters")
    val characters: ItemsDto?,
    @Json(name = "comics")
    val comics: ItemsDto?,
    @Json(name = "creators")
    val creators: ItemsDto?,
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
    val series: ItemsDto?,
    @Json(name = "start")
    val start: String?,
    @Json(name = "stories")
    val stories: ItemsDto?,
    @Json(name = "thumbnail")
    val thumbnail: ThumbnailDto?,
    @Json(name = "title")
    val title: String?,
    @Json(name = "urls")
    val urls: List<UrlDto>?
)
