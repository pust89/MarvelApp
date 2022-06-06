package com.pustovit.pdp.marvelapp.ui.characters.di

import com.pustovit.pdp.marvelapp.app.di.AppComponent
import com.pustovit.pdp.marvelapp.domain.repository.CharactersRepository
import com.pustovit.pdp.marvelapp.ui.characters.CharactersFragment
import com.pustovit.pdp.marvelapp.ui.characters.CharactersViewModel
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Scope


@CharactersScope
@Component(
    dependencies = [AppComponent::class],
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
