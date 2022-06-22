package com.pustovit.pdp.marvelapp.app.di

import android.content.Context
import coil.ImageLoader
import com.github.terrakok.cicerone.Router
import com.pustovit.pdp.marvelapp.app.MainActivity
import com.pustovit.pdp.marvelapp.app.di.module.CoilModule
import com.pustovit.pdp.marvelapp.app.di.module.LocalNavigationModule
import com.pustovit.pdp.marvelapp.app.di.module.NavigationModule
import com.pustovit.pdp.marvelapp.navigation.TabNavigationCharacters
import com.pustovit.pdp.marvelapp.navigation.TabNavigationEvents
import com.pustovit.pdp.marvelapp.app.TabContainerFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        CoilModule::class,
        NavigationModule::class,
        LocalNavigationModule::class
    ]
)
interface AppComponent {

    fun inject(mainActivity: MainActivity)

    fun inject(tabContainerFragment: TabContainerFragment)

    fun imageLoader(): ImageLoader

    @TabNavigationCharacters
    fun tabCharactersRouter(): Router

    @TabNavigationEvents
    fun tabEventsRouter(): Router

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun applicationContext(applicationContext: Context): Builder
        fun build(): AppComponent
    }

}