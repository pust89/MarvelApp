package com.pustovit.pdp.marvelapp.data.source.remote

import com.pustovit.pdp.marvelapp.data.source.remote.mapper.CharactersMapper
import com.pustovit.pdp.marvelapp.data.source.remote.network.MarvelService
import com.pustovit.pdp.marvelapp.domain.model.character.Character
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Pustovit V.V.
 * Date: 31.05.2022
 * Time: 18:48
 */

class CharactersRemoteDataSourceImpl @Inject constructor(
    private val charactersApi: MarvelService,
    private val charactersMapper: CharactersMapper
) : CharactersRemoteDataSource {

    override fun getCharacters(): Single<List<Character>> {
        return charactersApi.getCharacters().map(charactersMapper::mapResponse)
    }

}


interface CharactersRemoteDataSource {

    fun getCharacters(): Single<List<Character>>

}
