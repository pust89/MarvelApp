package com.pustovit.pdp.characters.data.source.remote.model

import com.pustovit.pdp.models.dto.ItemsDto
import com.pustovit.pdp.models.dto.ThumbnailDto
import com.pustovit.pdp.models.dto.UrlDto
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

