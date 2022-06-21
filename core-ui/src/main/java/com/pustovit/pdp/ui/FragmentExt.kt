package com.pustovit.pdp.ui

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.Router
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