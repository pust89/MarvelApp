package com.pustovit.pdp.network

import com.pustovit.pdp.network_api.CoreNetworkApi
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [CoreNetworkModule::class])
interface CoreNetworkComponent : CoreNetworkApi {

    companion object {
        @Volatile
        private var coreNetworkComponent: CoreNetworkComponent? = null

        fun get(): CoreNetworkComponent {
            if (coreNetworkComponent == null) {
                synchronized(CoreNetworkComponent::class.java) {
                    if (coreNetworkComponent == null) {
                        coreNetworkComponent = DaggerCoreNetworkComponent.builder().build()
                    }
                }
            }
            return coreNetworkComponent!!
        }
    }
}