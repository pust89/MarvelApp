package com.pustovit.pdp.marvelapp.navigation

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import java.util.HashMap

/**
 * Created by Pustovit V.V.
 * Date: 29.05.2022
 * Time: 13:44
 */
class CiceroneHolder {

    private val containers = HashMap<String, Cicerone<Router>>()

    fun getCicerone(tabNavigation: TabNavigation): Cicerone<Router> {
        val containerTag = tabNavigation.tag
        return containers.getOrPut(containerTag) {
            Cicerone.create()
        }
    }
}