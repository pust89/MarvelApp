package com.pustovit.pdp.characters.data.source.remote

import com.pustovit.pdp.characters_api.api.model.Character
import com.pustovit.pdp.characters.data.source.remote.mapper.CharactersMapper
import com.pustovit.pdp.characters.data.source.remote.service.CharactersService
import io.reactivex.Single
import javax.inject.Inject


class CharactersRemoteDataSourceImpl @Inject constructor(
    private val service: CharactersService,
    private val mapper: CharactersMapper
) : CharactersRemoteDataSource {

    override fun getCharacters(query: String): Single<List<Character>> {
        return run {
            if (query.isEmpty()) {
                service.getCharacters(limit = 50)
            } else service.getCharacters(query = query, limit = 99)
        }.map(mapper::map)
    }

    override fun getCharacter(characterId: Int): Single<Character> {
        return service.getCharacter(characterId).map { response ->
            val resultList = mapper.map(response)
            resultList.first()
        }
    }

    override fun getCharactersByEvent(eventId: Int): Single<List<Character>> {
        return service.getCharactersByEvent(eventId).map(mapper::map)
    }
}


interface CharactersRemoteDataSource {

    fun getCharacters(query: String): Single<List<Character>>

    fun getCharacter(characterId: Int): Single<Character>

    fun getCharactersByEvent(eventId: Int): Single<List<Character>>
}
