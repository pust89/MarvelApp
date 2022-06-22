package com.pustoivt.pdp.events_api.repository

import com.pustoivt.pdp.events_api.model.Event
import io.reactivex.Single

interface EventsRepository {

    fun getEvents(): Single<List<Event>>

    fun getEvent(eventId: Int): Single<Event>

}