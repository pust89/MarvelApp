package com.pustovit.pdp.marvelapp.app.di.dependencies

import com.pustovit.pdp.characters.di.CharactersFeatureDependencies
import com.pustovit.pdp.marvelapp.app.di.AppComponent
import com.pustovit.pdp.network_api.CoreNetworkApi
import com.pustovit.pdp.utils.di.FeatureScope
import dagger.Component

@FeatureScope
@Component(dependencies = [AppComponent::class, CoreNetworkApi::class])
interface CharactersFeatureDependenciesComponent : CharactersFeatureDependencies {
}