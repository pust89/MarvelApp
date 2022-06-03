package com.pustovit.pdp.marvelapp.data.source.remote.di

import com.pustovit.pdp.marvelapp.data.source.remote.CharactersRemoteDataSource
import com.pustovit.pdp.marvelapp.data.source.remote.CharactersRemoteDataSourceImpl
import com.pustovit.pdp.marvelapp.data.source.remote.network.CharactersApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Pustovit V.V.
 * Date: 02.06.2022
 * Time: 20:08
 */
@Module
class RemoteDataSourceModule {

    @Provides
    @Singleton
    fun provideCharactersRemoteDataSource(charactersApi: CharactersApi): CharactersRemoteDataSource {
        return CharactersRemoteDataSourceImpl(charactersApi)
    }
}