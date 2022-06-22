package com.pustoivt.pdp.events_api

import com.pustoivt.pdp.events_api.repository.EventsRepository

interface EventsApi {

    fun repository(): EventsRepository
}