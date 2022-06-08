package com.pustovit.pdp.marvelapp.data.source.remote.di

import com.pustovit.pdp.marvelapp.data.source.remote.CharactersRemoteDataSource
import com.pustovit.pdp.marvelapp.data.source.remote.CharactersRemoteDataSourceImpl
import com.pustovit.pdp.marvelapp.data.source.remote.EventsRemoteDataSource
import com.pustovit.pdp.marvelapp.data.source.remote.EventsRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

/**
 * Created by Pustovit V.V.
 * Date: 02.06.2022
 * Time: 20:08
 */
@Module
interface RemoteDataSourceModule {

    @Binds
    @Singleton
    fun bindCharactersRemoteDataSourceImpl(source: CharactersRemoteDataSourceImpl): CharactersRemoteDataSource

    @Binds
    @Singleton
    fun bindEventsRemoteDataSourceImpl(source: EventsRemoteDataSourceImpl): EventsRemoteDataSource

}