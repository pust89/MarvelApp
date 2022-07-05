package com.pustovit.pdp.common_models.mapper

import com.pustovit.pdp.common_models.domain.Items
import com.pustovit.pdp.common_models.domain.Summary
import com.pustovit.pdp.common_models.domain.Thumbnail
import com.pustovit.pdp.common_models.domain.UrlModel
import com.pustovit.pdp.common_models.dto.ItemsDto
import com.pustovit.pdp.common_models.dto.SummaryDto
import com.pustovit.pdp.common_models.dto.ThumbnailDto
import com.pustovit.pdp.common_models.dto.UrlDto
import com.pustovit.pdp.utils.orZero
import javax.inject.Inject

class CommonItemsMapper @Inject constructor() {

    fun map(dto: ThumbnailDto?): Thumbnail {
        return dto?.let {
            Thumbnail(
                path = it.path.orEmpty(),
                extension = it.extension.orEmpty()
            )
        } ?: Thumbnail()
    }

    fun map(dto: ItemsDto?): Items {
        return dto?.let {
            Items(
                available = it.available.orZero(),
                collectionURI = it.collectionURI.orEmpty(),
                items = map(it.items),
                returned = it.returned.orZero()
            )
        } ?: Items()
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