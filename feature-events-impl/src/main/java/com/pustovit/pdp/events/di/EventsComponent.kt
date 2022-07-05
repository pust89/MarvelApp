package com.pustovit.pdp.events.di

import com.pustoivt.pdp.events_api.EventsApi
import com.pustovit.pdp.events.ui.EventsFragment
import com.pustovit.pdp.injector.FeatureScope
import dagger.Component

@FeatureScope
@Component(
    dependencies = [EventsFeatureDependencies::class],
    modules = [ServiceModule::class,
        DataModule::class,
        ViewModelModule::class]
)
internal interface EventsComponent : EventsApi {

    fun inject(eventsFragment: EventsFragment)

}



