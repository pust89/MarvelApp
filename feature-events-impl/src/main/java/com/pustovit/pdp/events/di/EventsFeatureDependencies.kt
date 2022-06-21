package com.pustovit.pdp.events.di

import coil.Coil
import retrofit2.Retrofit

interface EventsFeatureDependencies {

    fun retrofit(): Retrofit

    fun coil(): Coil
}