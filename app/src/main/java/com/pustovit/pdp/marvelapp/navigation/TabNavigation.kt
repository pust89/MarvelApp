package com.pustovit.pdp.marvelapp.navigation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by Pustovit V.V.
 * Date: 30.05.2022
 * Time: 15:35
 */

sealed class TabNavigation(val tag: String) : Parcelable {
    @Parcelize
    object CHARACTERS : TabNavigation(tag = "characters")

    @Parcelize
    object EVENTS : TabNavigation(tag = "events")
}