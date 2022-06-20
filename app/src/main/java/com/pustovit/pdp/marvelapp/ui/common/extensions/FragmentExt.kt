package com.pustovit.pdp.marvelapp.ui.common.extensions

import android.widget.Toast
import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.Router
import com.pustovit.pdp.marvelapp.navigation.RouterProvider
import com.pustovit.pdp.marvelapp.common.mvi.ViewStateError

fun Fragment.mainRouter(): Router {
    return (requireActivity() as RouterProvider).router
}

fun Fragment.router(): Router {
    return (parentFragment as RouterProvider).router
}

fun Fragment.handleViewStateError(viewStateError: ViewStateError?) {
    viewStateError?.let {
        if (it.needHandle) {
            it.handle()
            Toast.makeText(requireContext(), it.error.message, Toast.LENGTH_SHORT).show()
        }
    }
}