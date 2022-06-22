package com.pustovit.pdp.events.di

import com.pustoivt.pdp.events_api.EventsApi
import com.pustovit.pdp.injector.ComponentHolder

object EventsComponentHolder : ComponentHolder<EventsApi, EventsFeatureDependencies> {
    @Volatile
    private var eventsComponent: EventsComponent? = null

    override fun init(dependencies: EventsFeatureDependencies) {
        synchronized(EventsComponentHolder::class.java) {
            if (eventsComponent == null) {
                eventsComponent =
                    DaggerEventsComponent.builder()
                        .eventsFeatureDependencies(dependencies)
                        .build()
            }
        }
    }

    override fun get(): EventsApi {
        checkNotNull(eventsComponent) { "EventsComponent was not initialized!" }
        return eventsComponent!!
    }

    internal fun getComponent(): EventsComponent {
        checkNotNull(eventsComponent) { "EventsComponent was not initialized!" }
        return eventsComponent!!
    }

    override fun reset() {
        eventsComponent = null
    }
}