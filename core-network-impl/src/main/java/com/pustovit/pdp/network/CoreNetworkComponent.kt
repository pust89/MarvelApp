package com.pustovit.pdp.network

import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [CoreNetworkModule::class])
interface CoreNetworkComponent : NetworkApi {

    override fun retrofit(): Retrofit
}