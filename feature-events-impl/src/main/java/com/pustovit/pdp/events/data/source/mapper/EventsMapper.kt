package com.pustovit.pdp.events.data.source.mapper

import com.pustoivt.pdp.events_api.model.Event
import com.pustovit.pdp.events.data.source.model.EventDto
import com.pustovit.pdp.common_models.mapper.CommonItemsMapper
import com.pustovit.pdp.common_models.response.MarvelResponse
import com.pustovit.pdp.utils.orZero
import javax.inject.Inject

class EventsMapper @Inject constructor(private val commonMapper: CommonItemsMapper) {

    fun map(response: MarvelResponse<EventDto>): List<Event> {
        return response.data?.results?.map { dto ->
            Event(
                characters = commonMapper.map(dto.characters),
                comics = commonMapper.map(dto.comics),
                creators = commonMapper.map(dto.creators),
                description = dto.description.orEmpty(),
                end = dto.end.orEmpty(),
                id = dto.id.orZero(),
                modified = dto.modified.orEmpty(),
                next = commonMapper.map(dto.next),
                previous = commonMapper.map(dto.previous),
                resourceURI = dto.resourceURI.orEmpty(),
                series = commonMapper.map(dto.series),
                start = dto.start.orEmpty(),
                stories = commonMapper.map(dto.stories),
                thumbnail = commonMapper.map(dto.thumbnail),
                title = dto.title.orEmpty(),
                urls = commonMapper.map(dto.urls),
            )
        } ?: emptyList<Event>()
    }
}