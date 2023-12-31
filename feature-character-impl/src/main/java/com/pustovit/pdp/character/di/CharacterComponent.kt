package com.pustovit.pdp.character.di

import com.pustovit.pdp.character.CharacterFragment
import com.pustovit.pdp.character.summary.SummaryFragment
import com.pustovit.pdp.character_api.CharacterApi
import com.pustovit.pdp.injector.FeatureScope
import dagger.Component


@FeatureScope
@Component(
    dependencies = [CharacterFeatureDependencies::class],
    modules = [ViewModelModule::class]
)
interface CharacterComponent : CharacterApi {

    fun inject(characterFragment: CharacterFragment)

    fun inject(summaryFragment: SummaryFragment)

}
