package com.pustivut.pdp.core_navigation

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import java.util.HashMap

class CiceroneHolder {

    private val containers = HashMap<String, Cicerone<Router>>()

    fun getCicerone(tabNavigation: TabNavigation): Cicerone<Router> {
        val containerTag = tabNavigation.tag
        return containers.getOrPut(containerTag) {
            Cicerone.create()
        }
    }
}