package com.pustovit.pdp.marvelapp.data.source.remote.network

import com.pustovit.pdp.marvelapp.data.source.remote.model.character.CharacterDto
import com.pustovit.pdp.marvelapp.data.source.remote.model.MarvelResponse
import com.pustovit.pdp.marvelapp.data.source.remote.model.events.EventDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Pustovit V.V.
 * Date: 31.05.2022
 * Time: 18:55
 */
interface MarvelService {

    @GET("/v1/public/characters")
    fun getCharacters(
        @Query("orderBy") orderBy: String = "-modified",
        @Query("limit") limit: Int = 99
    ): Single<MarvelResponse<CharacterDto>>

    @GET("/v1/public/events")
    fun getEvents(
        @Query("orderBy") orderBy: String = "startDate",
        @Query("limit") limit: Int = 50
    ): Single<MarvelResponse<EventDto>>

}