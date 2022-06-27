package com.pustovit.pdp.event.di

import com.pustovit.pdp.event_api.EventApi
import com.pustovit.pdp.injector.ComponentHolder
import com.pustovit.pdp.injector.ComponentHolderDelegate
import timber.log.Timber

object EventComponentHolder : ComponentHolder<EventApi, EventFeatureDependencies> {

    override var dependencyProvider: (() -> EventFeatureDependencies)? = null

    internal val component: EventComponent by
    ComponentHolderDelegate<EventFeatureDependencies, EventComponent> {
        DaggerEventComponent.builder().eventFeatureDependencies(it).build()
    }

    override fun get(): EventApi {
        return component
    }

}