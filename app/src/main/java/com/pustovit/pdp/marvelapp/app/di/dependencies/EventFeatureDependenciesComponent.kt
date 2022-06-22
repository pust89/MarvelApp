package com.pustovit.pdp.marvelapp.app.di.dependencies

import com.pustoivt.pdp.events_api.EventsApi
import com.pustovit.pdp.characters_api.api.CharactersApi
import com.pustovit.pdp.event.di.EventFeatureDependencies
import com.pustovit.pdp.marvelapp.app.di.AppComponent
import com.pustovit.pdp.utils.di.FeatureScope
import dagger.Component

@FeatureScope
@Component(
    dependencies = [AppComponent::class,
        CharactersApi::class,
        EventsApi::class]
)
interface EventFeatureDependenciesComponent : EventFeatureDependencies {
}