package com.pustovit.pdp.events.data.source.service

import com.pustovit.pdp.events.data.source.model.EventDto
import com.pustovit.pdp.models.response.MarvelResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EventsService {

    @GET("/v1/public/events")
    fun getEvents(
        @Query("orderBy") orderBy: String = "-modified",
        @Query("limit") limit: Int = 25
    ): Single<MarvelResponse<EventDto>>

    @GET("/v1/public/events/{eventId}")
    fun getEvent(
        @Path("eventId") eventId: Int
    ): Single<MarvelResponse<EventDto>>

}