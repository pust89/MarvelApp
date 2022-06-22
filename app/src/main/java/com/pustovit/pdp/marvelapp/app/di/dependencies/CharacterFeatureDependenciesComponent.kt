package com.pustovit.pdp.marvelapp.app.di.dependencies

import com.pustovit.pdp.character.di.CharacterFeatureDependencies
import com.pustovit.pdp.characters_api.api.CharactersApi
import com.pustovit.pdp.marvelapp.app.di.AppComponent
import com.pustovit.pdp.utils.di.FeatureScope
import dagger.Component

@FeatureScope
@Component(dependencies = [AppComponent::class, CharactersApi::class])
interface CharacterFeatureDependenciesComponent : CharacterFeatureDependencies {
}