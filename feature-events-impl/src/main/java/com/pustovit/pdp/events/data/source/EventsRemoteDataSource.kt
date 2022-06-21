package com.pustovit.pdp.events.data.source

import com.pustoivt.pdp.events.api.model.Event
import com.pustovit.pdp.events.data.source.mapper.EventsMapper
import com.pustovit.pdp.events.data.source.service.EventsService
import io.reactivex.Single
import javax.inject.Inject

class EventsRemoteDataSourceImpl @Inject constructor(
    private val service: EventsService,
    private val mapper: EventsMapper
) : EventsRemoteDataSource {

    override fun getEvents(): Single<List<Event>> {
        return service.getEvents().map(mapper::map)
    }

    override fun getEvent(eventId: Int): Single<Event> {
        return service.getEvent(eventId).map { response ->
            val resultList = mapper.map(response)
            resultList.first()
        }
    }


}

interface EventsRemoteDataSource {

    fun getEvents(): Single<List<Event>>

    fun getEvent(eventId: Int): Single<Event>

}