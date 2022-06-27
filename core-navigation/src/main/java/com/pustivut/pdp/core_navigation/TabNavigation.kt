package com.pustivut.pdp.core_navigation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

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
