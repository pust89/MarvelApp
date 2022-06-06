package com.pustovit.pdp.marvelapp.data.source.remote.mapper.di

import com.pustovit.pdp.marvelapp.data.source.remote.mapper.CommonItemsMapper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MapperModule {

    @Provides
    @Singleton
    fun provideCommonItemsMapper(): CommonItemsMapper {
        return CommonItemsMapper()
    }
}