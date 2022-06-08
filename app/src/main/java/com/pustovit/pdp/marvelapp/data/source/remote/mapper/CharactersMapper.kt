package com.pustovit.pdp.marvelapp.data.source.remote.mapper

import com.pustovit.pdp.marvelapp.common.extension.orZero
import com.pustovit.pdp.marvelapp.data.source.remote.model.character.CharacterDto
import com.pustovit.pdp.marvelapp.data.source.remote.model.MarvelResponse
import com.pustovit.pdp.marvelapp.domain.model.character.Character
import javax.inject.Inject

class CharactersMapper @Inject constructor(private val commonMapper: CommonItemsMapper) {

    fun mapResponse(response: MarvelResponse<CharacterDto>): List<Character> {
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