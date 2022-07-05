package com.pustovit.pdp.common_ui.ui

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.Router
import com.pustivut.pdp.core_navigation.RouterProvider
import com.pustivut.pdp.core_navigation.Screens
import com.pustivut.pdp.core_navigation.ScreensProvider
import com.pustovit.pdp.common_ui.ui.mvi.ViewStateError

fun Fragment.handleViewStateError(viewStateError: ViewStateError?) {
    viewStateError?.let {
        if (it.needHandle) {
            it.handle()
            Toast.makeText(requireContext(), it.error.message, Toast.LENGTH_SHORT).show()
        }
    }
}

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

val Fragment.mainRouter: Router
    get() = (requireActivity() as? RouterProvider)?.router
        ?: throw IllegalStateException("${requireActivity()} must implement RouterProvider")


val Fragment.router: Router
    get() = (parentFragment as? RouterProvider)?.router
        ?: throw IllegalStateException("$this must implement RouterProvider")

val Fragment.screens: Screens
    get() = (requireActivity() as? ScreensProvider)?.screens
        ?: throw IllegalStateException("${requireActivity()} must implement ScreensProvider")
