package com.umbrellait.unspot.core_network_impl

import com.umbrellait.unspot.core_network_api.CoreNetworkApi
import retrofit2.Retrofit

interface CoreNetworkComponent:CoreNetworkApi {

    override fun retrofit(): Retrofit {
        TODO("Not yet implemented")
    }
}