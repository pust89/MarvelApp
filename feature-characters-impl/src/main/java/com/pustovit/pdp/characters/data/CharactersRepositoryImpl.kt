package com.pustovit.pdp.characters.data

import com.pustovit.pdp.characters.api.model.Character
import com.pustovit.pdp.characters.api.repository.CharactersRepository
import com.pustovit.pdp.characters.data.source.remote.CharactersRemoteDataSource
import io.reactivex.Single
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val charactersRemoteDataSource: CharactersRemoteDataSource
) : CharactersRepository {

    override fun getCharacters(query: String): Single<List<com.pustovit.pdp.characters.api.model.Character>> {
        return charactersRemoteDataSource.getCharacters(query)
    }

    override fun getCharacter(characterId: Int): Single<Character> {
        return charactersRemoteDataSource.getCharacter(characterId)
    }

    override fun getCharactersByEvent(eventId: Int): Single<List<Character>> {
        return charactersRemoteDataSource.getCharactersByEvent(eventId)
    }
}