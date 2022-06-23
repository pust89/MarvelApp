package com.pustovit.pdp.event.di

import com.pustovit.pdp.event.EventFragment
import com.pustovit.pdp.event_api.EventApi
import com.pustovit.pdp.utils.di.FeatureScope
import dagger.Component

@FeatureScope
@Component(
    dependencies = [EventFeatureDependencies::class],
    modules = [ViewModelModule::class]
)
interface EventComponent : EventApi {

    fun inject(eventFragment: EventFragment)

}

