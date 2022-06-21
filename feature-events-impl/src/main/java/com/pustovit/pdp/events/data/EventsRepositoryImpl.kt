package com.pustovit.pdp.events.data

import com.pustoivt.pdp.events.api.model.Event
import com.pustoivt.pdp.events.api.repository.EventsRepository
import com.pustovit.pdp.events.data.source.EventsRemoteDataSource
import io.reactivex.Single
import javax.inject.Inject

class EventsRepositoryImpl @Inject constructor(private val remoteDataSource: EventsRemoteDataSource) :
    EventsRepository {

    override fun getEvents(): Single<List<Event>> {
        return remoteDataSource.getEvents()
    }

    override fun getEvent(eventId: Int): Single<Event> {
        return remoteDataSource.getEvent(eventId)
    }
}