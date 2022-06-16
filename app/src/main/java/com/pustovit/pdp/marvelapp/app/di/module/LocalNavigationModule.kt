package com.pustovit.pdp.marvelapp.app.di.module

import com.github.terrakok.cicerone.Router
import com.pustovit.pdp.marvelapp.navigation.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Pustovit V.V.
 * Date: 29.05.2022
 * Time: 13:45
 */
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

