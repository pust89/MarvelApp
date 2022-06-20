package com.pustovit.pdp.marvelapp.ui.common

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import kotlin.reflect.KClass

class BaseViewModelLazy<VM : BaseViewModel<*>>(
    private val viewModelClass: KClass<VM>,
    private val storeProducer: () -> ViewModelStore,
    private val factoryProducer: () -> ViewModelProvider.Factory
) : Lazy<VM> {
    private var cached: VM? = null

    override val value: VM
        get() {
            val viewModel = cached
            return if (viewModel == null) {
                val factory = factoryProducer()
                val store = storeProducer()
                ViewModelProvider(store, factory)[viewModelClass.java].also {
                    cached = it
                    it.onAttach()
                }
            } else {
                viewModel
            }
        }

    override fun isInitialized(): Boolean = cached != null
}