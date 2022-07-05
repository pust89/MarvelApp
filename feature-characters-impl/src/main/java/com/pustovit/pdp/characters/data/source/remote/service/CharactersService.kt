package com.pustovit.pdp.characters.data.source.remote.service

import com.pustovit.pdp.characters.data.source.remote.model.CharacterDto
import com.pustovit.pdp.common_models.response.MarvelResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharactersService {

    @GET("/v1/public/characters")
    fun getCharacters(
        @Query("nameStartsWith") query: String? = null,
        @Query("orderBy") orderBy: String = "name",
        @Query("limit") limit: Int = 99
    ): Single<MarvelResponse<CharacterDto>>

    @GET("/v1/public/characters/{characterId}")
    fun getCharacter(@Path("characterId") characterId: Int): Single<MarvelResponse<CharacterDto>>

    @GET("/v1/public/events/{eventId}/characters")
    fun getCharactersByEvent(
        @Path("eventId") eventId: Int
    ): Single<MarvelResponse<CharacterDto>>
}