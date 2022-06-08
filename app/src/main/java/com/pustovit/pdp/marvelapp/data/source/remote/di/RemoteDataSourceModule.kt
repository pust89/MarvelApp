package com.pustovit.pdp.marvelapp.data.source.remote.di

import com.pustovit.pdp.marvelapp.data.source.remote.CharactersRemoteDataSource
import com.pustovit.pdp.marvelapp.data.source.remote.CharactersRemoteDataSourceImpl
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
    fun bindsCharactersRemoteDataSource(remoteDataSource: CharactersRemoteDataSourceImpl): CharactersRemoteDataSource
}