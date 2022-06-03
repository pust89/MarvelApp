package com.pustovit.pdp.marvelapp.data.source.remote.network.di

import com.pustovit.pdp.marvelapp.data.source.remote.network.CharactersApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by Pustovit V.V.
 * Date: 31.05.2022
 * Time: 20:17
 */

@Module
class ServiceModule {

    @Provides
    @Singleton
    fun provideCharactersApi(retrofit: Retrofit): CharactersApi {
        return retrofit.create(CharactersApi::class.java)
    }
}