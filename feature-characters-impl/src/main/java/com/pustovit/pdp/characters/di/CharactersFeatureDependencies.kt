package com.pustovit.pdp.characters.di

import coil.ImageLoader
import retrofit2.Retrofit

interface CharactersFeatureDependencies {

    fun retrofit(): Retrofit

    fun imageLoader(): ImageLoader

}