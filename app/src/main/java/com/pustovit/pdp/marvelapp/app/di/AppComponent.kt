package com.pustovit.pdp.marvelapp.app.di

import android.content.Context
import coil.ImageLoader
import com.github.terrakok.cicerone.Router
import com.pustovit.pdp.marvelapp.app.MainActivity
import com.pustovit.pdp.marvelapp.app.di.module.*
import com.pustovit.pdp.marvelapp.data.repository.di.RepositoryModule
import com.pustovit.pdp.marvelapp.data.source.remote.di.RemoteDataSourceModule
import com.pustovit.pdp.marvelapp.data.source.remote.mapper.di.MapperModule
import com.pustovit.pdp.marvelapp.data.source.remote.network.di.NetworkModule
import com.pustovit.pdp.marvelapp.data.source.remote.network.di.ServiceModule
import com.pustovit.pdp.marvelapp.domain.repository.CharactersRepository
import com.pustovit.pdp.marvelapp.domain.repository.EventsRepository
import com.pustovit.pdp.marvelapp.navigation.TabNavigationCharacters
import com.pustovit.pdp.marvelapp.navigation.TabNavigationEvents
import com.pustovit.pdp.marvelapp.ui.tabcontainer.TabContainerFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Provider
import javax.inject.Singleton

/**
 * Created by Pustovit V.V.
 * Date: 29.05.2022
 * Time: 13:28
 */
@Singleton
@Component(
    modules = [
        CoilModule::class,
        NetworkModule::class,
        ServiceModule::class,
        MapperModule::class,
        RemoteDataSourceModule::class,
        RepositoryModule::class,
        NavigationModule::class,
        LocalNavigationModule::class
    ]
)
interface AppComponent {

    fun inject(mainActivity: MainActivity)

    fun inject(tabContainerFragment: TabContainerFragment)

    fun charactersRepository(): CharactersRepository

    fun eventsRepository(): EventsRepository

    fun imageLoader(): ImageLoader

    @TabNavigationCharacters
    fun tabCharacters(): Router

    @TabNavigationEvents
    fun tabEvents(): Router

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun applicationContext(applicationContext: Context): Builder
        fun build(): AppComponent
    }

}