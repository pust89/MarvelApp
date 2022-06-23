package com.pustovit.pdp.network

import com.pustovit.pdp.network_api.CoreNetworkApi
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [CoreNetworkModule::class])
interface CoreNetworkComponent : CoreNetworkApi