package com.pustovit.pdp.marvelapp.app

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import com.pustovit.pdp.marvelapp.BuildConfig
import com.pustovit.pdp.marvelapp.app.di.AppComponent
import com.pustovit.pdp.marvelapp.app.di.DaggerAppComponent
import timber.log.Timber
import javax.inject.Inject

class App : Application() {

    @Inject
    lateinit var features: Features

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .applicationContext(this)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        initTimber()
        appComponent.inject(this)
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}

fun Context.appComponent(): AppComponent {
    return (this.applicationContext as App).appComponent
}

fun Fragment.appComponent(): AppComponent {
    return requireContext().appComponent()
}