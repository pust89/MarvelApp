package com.pustovit.pdp.marvelapp.data.source.remote.network

import com.pustovit.pdp.marvelapp.data.source.remote.model.CharacterDto
import com.pustovit.pdp.marvelapp.data.source.remote.model.MarvelResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Pustovit V.V.
 * Date: 31.05.2022
 * Time: 18:55
 */
interface CharactersApi {

    @GET("/v1/public/characters")
    fun getCharacters(
        @Query("orderBy") orderBy: String = "-name",
        @Query("limit") limit: Int = 10
    ): Flowable<MarvelResponse<CharacterDto>>

}