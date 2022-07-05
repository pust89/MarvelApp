package com.pustovit.pdp.characters.di

import coil.ImageLoader
import com.pustivut.pdp.core_navigation.Screens
import com.pustovit.pdp.injector.FeatureDependencies
import retrofit2.Retrofit

interface CharactersFeatureDependencies : FeatureDependencies {

    fun retrofit(): Retrofit

    fun imageLoader(): ImageLoader

}