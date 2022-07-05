package com.pustovit.pdp.marvelapp.app.di.module

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.pustivut.pdp.core_navigation.CiceroneHolder
import dagger.Module
import dagger.Provides
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
class NavigationModule {

    private val cicerone: Cicerone<Router> = Cicerone.create()

    @Provides
    @MainRouter
    @Singleton
    fun provideRouter(): Router {
        return cicerone.router
    }

    @Provides
    @MainNavigatorHolder
    @Singleton
    fun provideNavigatorHolder(): NavigatorHolder {
        return cicerone.getNavigatorHolder()
    }

    @Provides
    @Singleton
    fun provideCiceroneHolder(): CiceroneHolder = CiceroneHolder()
}


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MainRouter

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MainNavigatorHolder