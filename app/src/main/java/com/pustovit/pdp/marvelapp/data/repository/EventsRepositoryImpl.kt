package com.pustovit.pdp.marvelapp.data.repository

import com.pustovit.pdp.marvelapp.data.source.remote.EventsRemoteDataSource
import com.pustovit.pdp.marvelapp.domain.model.event.Event
import com.pustovit.pdp.marvelapp.domain.repository.EventsRepository
import io.reactivex.Single
import timber.log.Timber
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