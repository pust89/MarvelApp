package com.pustovit.pdp.event.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pustovit.pdp.utils.di.FeatureScope
import javax.inject.Inject
import javax.inject.Provider

@FeatureScope
class ViewModelFactory @Inject constructor(
    private val viewModels: MutableMap<Class<out ViewModel>,
            @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return viewModels[modelClass]?.get() as T
    }
}