package com.pustovit.pdp.marvelapp.data.source.remote.mapper

import com.pustovit.pdp.marvelapp.common.extension.orZero
import com.pustovit.pdp.marvelapp.data.source.remote.model.*
import com.pustovit.pdp.marvelapp.domain.model.Item
import com.pustovit.pdp.marvelapp.domain.model.StoryItem
import com.pustovit.pdp.marvelapp.domain.model.Thumbnail
import com.pustovit.pdp.marvelapp.domain.model.UrlModel
import com.pustovit.pdp.marvelapp.domain.model.characters.Comics
import com.pustovit.pdp.marvelapp.domain.model.characters.Events
import com.pustovit.pdp.marvelapp.domain.model.characters.Series
import com.pustovit.pdp.marvelapp.domain.model.characters.Stories

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

    @JvmName("map1")
    fun map(items: List<ItemDto>?): List<Item> {
        return items?.map {
            Item(
                resourceURI = it.resourceURI.orEmpty(),
                name = it.name.orEmpty()
            )
        } ?: emptyList<Item>()
    }

    @JvmName("map2")
    fun map(items: List<StoryItemDto>?): List<StoryItem> {
        return items?.map {
            StoryItem(
                resourceURI = it.resourceURI.orEmpty(),
                name = it.name.orEmpty(),
                type = it.type.orEmpty()
            )
        } ?: emptyList<StoryItem>()
    }

    @JvmName("map3")
    fun map(urls: List<UrlDto>?): List<UrlModel> {
        return urls?.map {
            UrlModel(
                type = it.type.orEmpty(),
                url = it.url.orEmpty()
            )
        } ?: emptyList<UrlModel>()
    }

}