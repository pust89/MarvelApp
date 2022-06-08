package com.pustovit.pdp.marvelapp.data.repository.di

import com.pustovit.pdp.marvelapp.data.repository.CharactersRepositoryImpl
import com.pustovit.pdp.marvelapp.data.repository.EventsRepositoryImpl
import com.pustovit.pdp.marvelapp.data.source.remote.CharactersRemoteDataSource
import com.pustovit.pdp.marvelapp.data.source.remote.CharactersRemoteDataSourceImpl
import com.pustovit.pdp.marvelapp.domain.repository.CharactersRepository
import com.pustovit.pdp.marvelapp.domain.repository.EventsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Pustovit V.V.
 * Date: 02.06.2022
 * Time: 20:17
 */
@Module
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindCharactersRepositoryImpl(repository: CharactersRepositoryImpl): CharactersRepository

    @Binds
    @Singleton
    fun bindEventsRepositoryImpl(repository: EventsRepositoryImpl): EventsRepository

}