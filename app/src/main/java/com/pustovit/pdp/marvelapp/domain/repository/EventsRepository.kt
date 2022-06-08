package com.pustovit.pdp.marvelapp.domain.repository

import com.pustovit.pdp.marvelapp.domain.model.event.Event
import io.reactivex.Single

interface EventsRepository {

    fun getEvents(): Single<List<Event>>
}