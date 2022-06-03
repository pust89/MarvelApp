package com.pustovit.pdp.marvelapp.ui.extensions

import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.Router
import com.pustovit.pdp.marvelapp.navigation.RouterProvider

/**
 * Created by Pustovit V.V.
 * Date: 30.05.2022
 * Time: 15:42
 */

fun Fragment.mainRouter(): Router {
    return (requireActivity() as RouterProvider).router
}

fun Fragment.router(): Router {
    return (parentFragment as RouterProvider).router
}