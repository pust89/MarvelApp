package com.pustovit.pdp.marvelapp.ui.characters.di

import com.pustovit.pdp.marvelapp.app.di.AppComponent
import com.pustovit.pdp.marvelapp.ui.characters.CharactersFragment
import dagger.Component
import javax.inject.Scope


@CharactersScope
@Component(
    dependencies = [AppComponent::class],
    modules = [ViewModelModule::class]
)
interface CharactersComponent {

    fun inject(charactersFragment: CharactersFragment)

    @Component.Builder
    interface Builder {

        fun appComponent(appComponent: AppComponent): Builder

        fun build(): CharactersComponent
    }

}

@Scope
@Retention(value = AnnotationRetention.RUNTIME)
annotation class CharactersScope
