package com.pustovit.pdp.network

import com.pustovit.pdp.utils.di.FeatureScope
import com.pustovit.pdp.network_api.CoreNetworkApi
import dagger.Component
import retrofit2.Retrofit

@FeatureScope
@Component(modules = [CoreNetworkModule::class])
interface CoreNetworkComponent : CoreNetworkApi {

    override fun retrofit(): Retrofit
}