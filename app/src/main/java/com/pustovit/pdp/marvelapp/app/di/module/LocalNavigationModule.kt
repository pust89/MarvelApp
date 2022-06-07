package com.pustovit.pdp.marvelapp.app.di.module

import com.github.terrakok.cicerone.Router
import com.pustovit.pdp.marvelapp.navigation.CiceroneHolder
import com.pustovit.pdp.marvelapp.navigation.TabNavigation
import com.pustovit.pdp.marvelapp.navigation.TabNavigationCharacters
import com.pustovit.pdp.marvelapp.navigation.TabNavigationEvents
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
    fun provideCharactersRouter(ciceroneHolder: CiceroneHolder): Router {
        return ciceroneHolder.getCicerone(TabNavigation.CHARACTERS).router
    }

    @Singleton
    @Provides
    @TabNavigationEvents
    fun provideEventsRouter(ciceroneHolder: CiceroneHolder): Router {
        return ciceroneHolder.getCicerone(TabNavigation.EVENTS).router
    }

}

