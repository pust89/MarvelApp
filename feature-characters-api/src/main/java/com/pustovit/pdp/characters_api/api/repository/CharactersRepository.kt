package com.pustovit.pdp.characters_api.api.repository

import com.pustovit.pdp.characters_api.api.model.Character
import io.reactivex.Single

interface CharactersRepository {

    fun getCharacters(query: String): Single<List<Character>>

    fun getCharacter(characterId: Int): Single<Character>

    fun getCharactersByEvent(eventId: Int): Single<List<Character>>
}
