package com.pustovit.pdp.events.di

import coil.ImageLoader
import com.pustovit.pdp.injector.FeatureDependencies
import retrofit2.Retrofit

interface EventsFeatureDependencies: FeatureDependencies {

    fun retrofit(): Retrofit

    fun imageLoader(): ImageLoader

}