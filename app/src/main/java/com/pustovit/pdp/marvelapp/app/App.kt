package com.pustovit.pdp.marvelapp.app

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import com.pustovit.pdp.marvelapp.app.di.AppComponent
import com.pustovit.pdp.marvelapp.app.di.DaggerAppComponent

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

    }
}

fun Context.appComponent(): AppComponent {
    return (this.applicationContext as App).appComponent
}

fun Fragment.appComponent():AppComponent {
    return requireContext().appComponent()
}