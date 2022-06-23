package com.pustivut.pdp.core_navigation

import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.Router


val Fragment.mainRouter: Router
    get() = (requireActivity() as? RouterProvider)?.router
        ?: throw IllegalStateException("${requireActivity()} must implement RouterProvider")


val Fragment.router: Router
    get() = (parentFragment as? RouterProvider)?.router
        ?: throw IllegalStateException("$this must implement RouterProvider")

val Fragment.screens: Screens
    get() = (requireActivity() as? ScreensProvider)?.screens
        ?: throw IllegalStateException("${requireActivity()} must implement ScreensProvider")
