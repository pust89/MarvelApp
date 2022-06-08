package com.pustovit.pdp.marvelapp.data.source.remote.mapper

import com.pustovit.pdp.marvelapp.common.extension.orZero
import com.pustovit.pdp.marvelapp.data.source.remote.model.common.*
import com.pustovit.pdp.marvelapp.domain.model.common.*

class CommonItemsMapper {

    fun map(dto: ThumbnailDto?): Thumbnail {
        return dto?.let {
            Thumbnail(
                path = it.path.orEmpty(),
                extension = it.extension.orEmpty()
            )
        } ?: Thumbnail()
    }

    fun map(dto: ComicsDto?): Comics {
        return dto?.let {
            Comics(
                available = it.available.orZero(),
                collectionURI = it.collectionURI.orEmpty(),
                items = map(it.items),
                returned = it.returned.orZero()
            )
        } ?: Comics()
    }

    fun map(dto: SeriesDto?): Series {
        return dto?.let {
            Series(
                available = it.available.orZero(),
                collectionURI = it.collectionURI.orEmpty(),
                items = map(it.items)
            )
        } ?: Series()
    }

    fun map(dto: StoriesDto?): Stories {
        return dto?.let {
            Stories(
                available = it.available.orZero(),
                collectionURI = it.collectionURI.orEmpty(),
                items = map(it.items)
            )
        } ?: Stories()
    }

    fun map(dto: EventsDto?): Events {
        return dto?.let {
            Events(
                available = it.available.orZero(),
                collectionURI = it.collectionURI.orEmpty(),
                items = map(it.items),
                returned = it.returned.orZero()
            )
        } ?: Events()
    }

    fun map(dto: CharactersDto?): Characters {
        return dto?.let {
            Characters(
                available = it.available.orZero(),
                collectionURI = it.collectionURI.orEmpty(),
                items = map(it.items),
                returned = it.returned.orZero()
            )
        } ?: Characters()
    }

    fun map(dto: CreatorsDto?): Creators {
        return dto?.let {
            Creators(
                available = it.available.orZero(),
                collectionURI = it.collectionURI.orEmpty(),
                items = map(it.items),
                returned = it.returned.orZero()
            )
        } ?: Creators()
    }

    fun map(dto: SummaryDto?): Summary {
        return dto?.let {
            Summary(
                resourceURI = it.resourceURI.orEmpty(),
                name = it.name.orEmpty()
            )
        } ?: Summary()
    }

    @JvmName("mapSummaryDto")
    fun map(items: List<SummaryDto>?): List<Summary> {
        return items?.map {
            Summary(
                resourceURI = it.resourceURI.orEmpty(),
                name = it.name.orEmpty()
            )
        } ?: emptyList<Summary>()
    }

    @JvmName("mapStorySummaryDto")
    fun map(items: List<StorySummaryDto>?): List<StorySummary> {
        return items?.map {
            StorySummary(
                resourceURI = it.resourceURI.orEmpty(),
                name = it.name.orEmpty(),
                type = it.type.orEmpty()
            )
        } ?: emptyList<StorySummary>()
    }

    @JvmName("mapCharacterSummaryDto")
    fun map(items: List<CharacterSummaryDto>?): List<CharacterSummary> {
        return items?.map {
            CharacterSummary(
                resourceURI = it.resourceURI.orEmpty(),
                name = it.name.orEmpty(),
                role = it.role.orEmpty()
            )
        } ?: emptyList<CharacterSummary>()
    }

    @JvmName("mapUrlDto")
    fun map(urls: List<UrlDto>?): List<UrlModel> {
        return urls?.map {
            UrlModel(
                type = it.type.orEmpty(),
                url = it.url.orEmpty()
            )
        } ?: emptyList<UrlModel>()
    }


}