package com.pustovit.pdp.marvelapp.data.source.remote

import com.pustovit.pdp.marvelapp.data.source.remote.mapper.CharactersMapper
import com.pustovit.pdp.marvelapp.data.source.remote.network.MarvelService
import com.pustovit.pdp.marvelapp.domain.model.character.Character
import io.reactivex.Single
import java.lang.RuntimeException
import javax.inject.Inject

/**
 * Created by Pustovit V.V.
 * Date: 31.05.2022
 * Time: 18:48
 */

class CharactersRemoteDataSourceImpl @Inject constructor(
    private val service: MarvelService,
    private val mapper: CharactersMapper
) : CharactersRemoteDataSource {

    //    override fun getCharacters(query: String): Single<List<Character>> {
//        val response = if (query.isEmpty()) {
//            service.getCharacters(limit = 20)
//        } else service.getCharacters(query = query, limit = 99)
//        return response.map(mapper::map)
//    }

    override fun getCharacters(query: String): Single<List<Character>> {
        return run {
            if (query.isEmpty()) {
                service.getCharacters(limit = 20)
            } else service.getCharacters(query = query, limit = 99)
        }.map(mapper::map)
    }

    override fun getCharacter(characterId: Int): Single<Character> {
        return service.getCharacter(characterId).map { response ->
            val resultList = mapper.map(response)
            resultList.first()
        }
    }

}


interface CharactersRemoteDataSource {

    fun getCharacters(query: String): Single<List<Character>>

    fun getCharacter(characterId: Int): Single<Character>
}
