package com.pustovit.pdp.characters.di

import coil.Coil
import retrofit2.Retrofit

interface CharactersFeatureDependencies {

    fun retrofit(): Retrofit

    fun coil(): Coil
}