package com.pustovit.pdp.characters.di

import androidx.lifecycle.ViewModel
import com.pustovit.pdp.characters_api.api.CharactersApi
import com.pustovit.pdp.characters.ui.CharactersFragment
import com.pustovit.pdp.characters.ui.CharactersViewModel
import com.pustovit.pdp.utils.di.FeatureScope
import com.pustovit.pdp.common_ui.di.ViewModelKey
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.multibindings.IntoMap

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

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CharactersViewModel::class)
    fun bindCharactersViewModel(viewModel: CharactersViewModel): ViewModel

}



