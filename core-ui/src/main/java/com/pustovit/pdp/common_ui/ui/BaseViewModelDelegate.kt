package com.pustovit.pdp.common_ui.ui

import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.lifecycle.HasDefaultViewModelProviderFactory
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import kotlin.reflect.KClass


@MainThread
inline fun <reified VM : BaseViewModel<*>> Fragment.baseViewModels(
    noinline ownerProducer: () -> ViewModelStoreOwner = { this },
    noinline factoryProducer: (() -> ViewModelProvider.Factory)? = null
    ): Lazy<VM> {
    val lazyViewModel = createBaseViewModelLazy(
        viewModelClass = VM::class,
        storeProducer = { ownerProducer().viewModelStore },
        factoryProducer = factoryProducer ?: {
            (ownerProducer() as? HasDefaultViewModelProviderFactory)?.defaultViewModelProviderFactory
                ?: defaultViewModelProviderFactory
        }
    )
    return lazyViewModel
}

@MainThread
fun <VM : BaseViewModel<*>> Fragment.createBaseViewModelLazy(
    viewModelClass: KClass<VM>,
    storeProducer: () -> ViewModelStore,
    factoryProducer: (() -> ViewModelProvider.Factory)? = null
): Lazy<VM> {
    val factoryPromise = factoryProducer ?: {
        defaultViewModelProviderFactory
    }
    return BaseViewModelLazy(viewModelClass, storeProducer, factoryPromise)
}