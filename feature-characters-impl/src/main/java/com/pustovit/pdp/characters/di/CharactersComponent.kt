package com.pustovit.pdp.characters.di

import com.pustovit.pdp.characters.ui.CharactersFragment
import com.pustovit.pdp.characters_api.api.CharactersApi
import com.pustovit.pdp.injector.FeatureScope
import dagger.Component

@FeatureScope
@Component(
    dependencies = [CharactersFeatureDependencies::class],
    modules = [ServiceModule::class,
        DataModule::class,
        ViewModelModule::class,
        UiModule::class]
)
interface CharactersComponent : CharactersApi {

    fun inject(charactersFragment: CharactersFragment)

}




