package com.pustovit.pdp.marvelapp.app

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import com.pustovit.pdp.marvelapp.BuildConfig
import com.pustovit.pdp.marvelapp.app.di.AppComponent
import com.pustovit.pdp.marvelapp.app.di.DaggerAppComponent
import timber.log.Timber

/**
 * Created by Pustovit V.V.
 * Date: 29.05.2022
 * Time: 13:18
 */
class App : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .applicationContext(this)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        initTimber()
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

fun Fragment.appComponent():AppComponent {
    return requireContext().appComponent()
}