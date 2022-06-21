package com.pustovit.pdp.characters.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

@CharactersScope
class ViewModelFactory @Inject constructor(
    private val viewModels: MutableMap<Class<out ViewModel>,
            @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return viewModels[modelClass]?.get() as T
    }
}