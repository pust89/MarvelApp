package com.pustovit.pdp.marvelapp.data.source.remote

import com.pustovit.pdp.marvelapp.data.source.remote.model.CharacterDto
import com.pustovit.pdp.marvelapp.data.source.remote.model.MarvelResponse
import com.pustovit.pdp.marvelapp.data.source.remote.network.CharactersApi
import com.pustovit.pdp.marvelapp.domain.model.Character
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Created by Pustovit V.V.
 * Date: 31.05.2022
 * Time: 18:48
 */

class CharactersRemoteDataSourceImpl @Inject constructor(
    private val charactersApi: CharactersApi
) : CharactersRemoteDataSource {

    override fun getCharacters(): Flowable<Result<List<Character>>> {
        return charactersApi.getCharacters().map { response ->
            mapResponse(response)
        }
    }

    private fun mapResponse(response: MarvelResponse<CharacterDto>): Result<List<Character>> {
        val characters: List<Character> = response.data?.results?.map { dto ->
            Character(
                id = dto.id ?: 0,
                name = dto.name ?: "",
                description = dto.description ?: ""
            )
        } ?: emptyList()
        return Result.success(characters)
    }

}

interface CharactersRemoteDataSource {

    fun getCharacters(): Flowable<Result<List<Character>>>

}
