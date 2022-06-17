package com.pustovit.pdp.marvelapp.data.source.remote.network

import com.pustovit.pdp.marvelapp.data.source.remote.model.character.CharacterDto
import com.pustovit.pdp.marvelapp.data.source.remote.model.MarvelResponse
import com.pustovit.pdp.marvelapp.data.source.remote.model.events.EventDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

/**
 * Created by Pustovit V.V.
 * Date: 31.05.2022
 * Time: 18:55
 */
interface MarvelService {

    @GET("/v1/public/characters")
    fun getCharacters(
        @Query("nameStartsWith") query: String? = null,
        @Query("orderBy") orderBy: String = "name",
        @Query("limit") limit: Int = 99
    ): Single<MarvelResponse<CharacterDto>>

    @GET("/v1/public/characters/{characterId}")
    fun getCharacter(@Path("characterId") characterId: Int): Single<MarvelResponse<CharacterDto>>

    @GET("/v1/public/events")
    fun getEvents(
        @Query("orderBy") orderBy: String = "-modified",
        @Query("limit") limit: Int = 25
    ): Single<MarvelResponse<EventDto>>

    @GET("/v1/public/events/{eventId}")
    fun getEvent(
        @Path("eventId") eventId: Int
    ): Single<MarvelResponse<EventDto>>

    @GET("/v1/public/events/{eventId}/characters")
    fun getCharactersByEvent(
        @Path("eventId") eventId: Int
    ): Single<MarvelResponse<CharacterDto>>
}