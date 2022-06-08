package com.pustovit.pdp.marvelapp.data.source.remote.mapper

import com.pustovit.pdp.marvelapp.common.extension.orZero
import com.pustovit.pdp.marvelapp.data.source.remote.model.MarvelResponse
import com.pustovit.pdp.marvelapp.data.source.remote.model.events.EventDto
import com.pustovit.pdp.marvelapp.domain.model.common.*
import com.pustovit.pdp.marvelapp.domain.model.event.Event
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