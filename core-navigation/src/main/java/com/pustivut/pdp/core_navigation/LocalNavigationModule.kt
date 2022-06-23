package com.pustivut.pdp.core_navigation

import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalNavigationModule {

    @Provides
    @Singleton
    fun provideLocalNavigationHolder(): CiceroneHolder = CiceroneHolder()

    @Singleton
    @Provides
    @TabNavigationCharacters
    fun provideCharactersTabRouter(ciceroneHolder: CiceroneHolder): Router {
        return ciceroneHolder.getCicerone(TabNavigation.CHARACTERS).router
    }

    @Singleton
    @Provides
    @TabNavigationEvents
    fun provideEventsTabRouter(ciceroneHolder: CiceroneHolder): Router {
        return ciceroneHolder.getCicerone(TabNavigation.EVENTS).router
    }

}

