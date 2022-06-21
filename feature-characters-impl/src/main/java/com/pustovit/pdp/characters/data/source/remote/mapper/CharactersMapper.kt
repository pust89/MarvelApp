package com.pustovit.pdp.characters.data.source.remote.mapper

import com.pustovit.pdp.characters.api.model.Character
import com.pustovit.pdp.characters.data.source.remote.model.CharacterDto
import com.pustovit.pdp.models.mapper.CommonItemsMapper
import com.pustovit.pdp.models.response.MarvelResponse
import com.pustovit.pdp.utils.orZero
import javax.inject.Inject


class CharactersMapper @Inject constructor(private val commonMapper: CommonItemsMapper) {

    fun map(response: MarvelResponse<CharacterDto>): List<Character> {
        return response.data?.results?.map { dto ->
            Character(
                id = dto.id.orZero(),
                name = dto.name.orEmpty(),
                description = dto.description.orEmpty(),
                modified = dto.modified.orEmpty(),
                resourceURI = dto.resourceURI.orEmpty(),
                thumbnail = commonMapper.map(dto.thumbnail),
                comics = commonMapper.map(dto.comics),
                series = commonMapper.map(dto.series),
                stories = commonMapper.map(dto.stories),
                events = commonMapper.map(dto.events),
                urls = commonMapper.map(dto.urls)
            )
        } ?: emptyList()
    }

}