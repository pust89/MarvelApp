package com.pustovit.pdp.events.di

import com.pustoivt.pdp.events_api.EventsApi
import com.pustovit.pdp.injector.ComponentHolder
import com.pustovit.pdp.injector.ComponentHolderDelegate

object EventsComponentHolder : ComponentHolder<EventsApi, EventsFeatureDependencies> {

    override var dependencyProvider: (() -> EventsFeatureDependencies)? = null

    internal val component by ComponentHolderDelegate<EventsFeatureDependencies, EventsComponent> { dependencies ->
        DaggerEventsComponent.builder().eventsFeatureDependencies(dependencies).build()
    }
    override fun get(): EventsApi {
        return component
    }

}