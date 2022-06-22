package com.pustovit.pdp.events.di

import coil.ImageLoader
import retrofit2.Retrofit

interface EventsFeatureDependencies {

    fun retrofit(): Retrofit

    fun imageLoader(): ImageLoader

}