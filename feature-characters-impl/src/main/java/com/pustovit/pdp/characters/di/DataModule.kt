package com.pustovit.pdp.characters.di

import com.pustovit.pdp.characters_api.api.repository.CharactersRepository
import com.pustovit.pdp.characters.data.CharactersRepositoryImpl
import com.pustovit.pdp.characters.data.source.remote.CharactersRemoteDataSource
import com.pustovit.pdp.characters.data.source.remote.CharactersRemoteDataSourceImpl
import com.pustovit.pdp.injector.FeatureScope
import dagger.Binds
import dagger.Module

@Module
interface DataModule {

    @Binds
    @FeatureScope
    fun bindCharactersRepository(repository: CharactersRepositoryImpl)
            : CharactersRepository

    @Binds
    fun bindCharactersRemoteDataSource(source: CharactersRemoteDataSourceImpl)
            : CharactersRemoteDataSource
}