package com.pustovit.pdp.marvelapp.data.source.remote

import com.pustovit.pdp.marvelapp.data.source.remote.mapper.EventsMapper
import com.pustovit.pdp.marvelapp.data.source.remote.network.MarvelService
import com.pustovit.pdp.marvelapp.domain.model.character.Character
import com.pustovit.pdp.marvelapp.domain.model.event.Event
import io.reactivex.Single
import javax.inject.Inject

class EventsRemoteDataSourceImpl @Inject constructor(
    private val service: MarvelService,
    private val mapper: EventsMapper
) : EventsRemoteDataSource {

    override fun getEvents(): Single<List<Event>> {
        return service.getEvents().map(mapper::map)
    }

}

interface EventsRemoteDataSource {
    fun getEvents(): Single<List<Event>>
}