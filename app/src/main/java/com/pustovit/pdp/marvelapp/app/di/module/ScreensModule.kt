package com.pustovit.pdp.marvelapp.app.di.module

import com.pustivut.pdp.core_navigation.Screens
import com.pustovit.pdp.marvelapp.navigation.ScreensImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface ScreensModule {

    @Binds
    @Singleton
    fun bindScreensImpl(screensImpl: ScreensImpl): Screens

}