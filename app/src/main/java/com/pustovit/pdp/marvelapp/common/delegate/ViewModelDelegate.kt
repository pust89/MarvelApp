package com.pustovit.pdp.marvelapp.common.delegate

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.createViewModelLazy
import androidx.fragment.app.viewModels
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pustovit.pdp.marvelapp.ui.common.BaseViewModel
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

//class BaseViewModelDelegate<T : BaseViewModel<*>>(private val factory: ViewModelProvider.Factory) :
//    DefaultLifecycleObserver,
//
//    ReadOnlyProperty<Fragment, T> {
//    private var vm: T? = null
//
//    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
//        thisRef.createViewModelLazy(T::class,
//        thisRef,
//        factory)
//        return vm!!
//    }
//
//    override fun onCreate(owner: LifecycleOwner) {
//        vm?.onAttach()
//    }
//}