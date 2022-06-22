package com.pustovit.pdp.event.di

import com.pustovit.pdp.event_api.EventApi
import com.pustovit.pdp.injector.ComponentHolder

object EventComponentHolder : ComponentHolder<EventApi, EventFeatureDependencies> {

    @Volatile
    private var eventComponent: EventComponent? = null

    override fun init(dependencies: EventFeatureDependencies) {
        synchronized(EventComponentHolder::class) {
            if (eventComponent == null) {
                eventComponent =
                    DaggerEventComponent.builder().eventFeatureDependencies(dependencies).build()
            }
        }
    }

    override fun get(): EventApi {
        requireNotNull(eventComponent) { "EventComponent was not initialized!" }
        return eventComponent!!
    }

    internal fun getComponent(): EventComponent {
        requireNotNull(eventComponent) { "EventComponent was not initialized!" }
        return eventComponent!!
    }

    override fun reset() {
        eventComponent = null
    }
}