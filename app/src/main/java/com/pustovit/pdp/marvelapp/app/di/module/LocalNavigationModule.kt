package com.pustovit.pdp.marvelapp.app.di.module

import com.pustovit.pdp.marvelapp.navigation.CiceroneHolder
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
}