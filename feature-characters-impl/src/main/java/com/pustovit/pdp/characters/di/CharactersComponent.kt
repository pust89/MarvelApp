package com.pustovit.pdp.characters.di

import androidx.lifecycle.ViewModel
import com.pustovit.pdp.characters.ui.CharactersFragment
import com.pustovit.pdp.characters.ui.CharactersViewModel
import com.pustovit.pdp.di.ViewModelKey
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Scope


@CharactersScope
@Component(
    dependencies = [CharactersFeatureDependencies::class],
    modules = [ViewModelModule::class]
)
interface CharactersComponent {

    fun inject(charactersFragment: CharactersFragment)

    @Component.Builder
    interface Builder {

        fun dependencies(dependencies: CharactersFeatureDependencies): Builder

        fun build(): CharactersComponent
    }

}

@Scope
@Retention(value = AnnotationRetention.RUNTIME)
annotation class CharactersScope

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CharactersViewModel::class)
    fun bindCharactersViewModel(viewModel: CharactersViewModel): ViewModel

}