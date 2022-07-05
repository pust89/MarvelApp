package com.pustoivt.pdp.events_api

import com.pustoivt.pdp.events_api.repository.EventsRepository
import com.pustovit.pdp.injector.FeatureApi

interface EventsApi : FeatureApi {

    fun repository(): EventsRepository
}