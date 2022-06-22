package com.pustovit.pdp.marvelapp.navigation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import javax.inject.Qualifier

sealed class TabNavigation(val tag: String) : Parcelable {
    @Parcelize
    object CHARACTERS : TabNavigation(tag = characters)

    @Parcelize
    object EVENTS : TabNavigation(tag = events)

    companion object{
        const val characters = "characters"
        const val events = "events"
    }
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class TabNavigationCharacters

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class TabNavigationEvents