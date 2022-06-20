package com.pustovit.pdp.marvelapp.ui.common

import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import io.reactivex.disposables.CompositeDisposable
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class CompositeDisposableDelegate : ReadOnlyProperty<Fragment, CompositeDisposable>,
    DefaultLifecycleObserver {

    private val compositeDisposable = CompositeDisposable()

    override fun getValue(thisRef: Fragment, property: KProperty<*>): CompositeDisposable {
        thisRef.lifecycle.addObserver(this)
        return compositeDisposable
    }

    override fun onDestroy(owner: LifecycleOwner) {
        compositeDisposable.clear()
        super.onDestroy(owner)
    }
}