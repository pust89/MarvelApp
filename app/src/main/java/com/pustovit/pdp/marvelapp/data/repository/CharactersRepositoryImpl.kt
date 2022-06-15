package com.pustovit.pdp.marvelapp.data.repository

import com.pustovit.pdp.marvelapp.data.source.remote.CharactersRemoteDataSource
import com.pustovit.pdp.marvelapp.domain.model.character.Character
import com.pustovit.pdp.marvelapp.domain.repository.CharactersRepository
import io.reactivex.Single
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val charactersRemoteDataSource: CharactersRemoteDataSource
) : CharactersRepository {

    override fun getCharacters(query: String): Single<List<Character>> {
        return charactersRemoteDataSource.getCharacters(query)
    }

    override fun getCharacter(characterId: Int): Single<Character> {
        return charactersRemoteDataSource.getCharacter(characterId)
    }
}