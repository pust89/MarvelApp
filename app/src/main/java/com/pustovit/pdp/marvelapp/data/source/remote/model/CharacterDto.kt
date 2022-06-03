package com.pustovit.pdp.marvelapp.data.source.remote.model


import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Keep
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
    val series: Series?,
    @Json(name = "stories")
    val stories: StoriesDto?,
    @Json(name = "events")
    val events: EventsDto?,
    @Json(name = "urls")
    val urls: List<UrlDto>?
)

@Keep
@JsonClass(generateAdapter = true)
data class ThumbnailDto(
    @Json(name = "path")
    val path: String?,
    @Json(name = "extension")
    val extension: String?
)

@Keep
@JsonClass(generateAdapter = true)
data class ComicsDto(
    @Json(name = "available")
    val available: Int?,
    @Json(name = "collectionURI")
    val collectionURI: String?,
    @Json(name = "items")
    val items: List<ItemDto>?,
    @Json(name = "returned")
    val returned: Int?
)

@Keep
@JsonClass(generateAdapter = true)
data class ItemDto(
    @Json(name = "resourceURI")
    val resourceURI: String?,
    @Json(name = "name")
    val name: String?
)

@Keep
@JsonClass(generateAdapter = true)
data class Series(
    @Json(name = "available")
    val available: Int?,
    @Json(name = "collectionURI")
    val collectionURI: String?,
    @Json(name = "items")
    val items: List<ItemDto>?,
    @Json(name = "returned")
    val returned: Int?
)

@Keep
@JsonClass(generateAdapter = true)
data class StoriesDto(
    @Json(name = "available")
    val available: Int?,
    @Json(name = "collectionURI")
    val collectionURI: String?,
    @Json(name = "items")
    val items: List<StoryItemDto>?,
    @Json(name = "returned")
    val returned: Int?
)

@Keep
@JsonClass(generateAdapter = true)
data class StoryItemDto(
    @Json(name = "resourceURI")
    val resourceURI: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "type")
    val type: String?
)

@Keep
@JsonClass(generateAdapter = true)
data class EventsDto(
    @Json(name = "available")
    val available: Int?,
    @Json(name = "collectionURI")
    val collectionURI: String?,
    @Json(name = "items")
    val items: List<ItemDto>?,
    @Json(name = "returned")
    val returned: Int?
)

@Keep
@JsonClass(generateAdapter = true)
data class UrlDto(
    @Json(name = "type")
    val type: String?,
    @Json(name = "url")
    val url: String?
)

