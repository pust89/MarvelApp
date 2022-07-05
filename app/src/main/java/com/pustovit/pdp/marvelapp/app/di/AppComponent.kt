package com.pustovit.pdp.marvelapp.app.di

import android.content.Context
import com.pustovit.pdp.marvelapp.app.App
import com.pustovit.pdp.marvelapp.app.MainActivity
import com.pustovit.pdp.marvelapp.app.TabContainerFragment
import com.pustovit.pdp.marvelapp.app.di.module.*
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        CoilModule::class,
        NavigationModule::class,
        ScreensModule::class,
        FeatureApiModule::class
    ]
)
interface AppComponent {

    fun inject(app: App)

    fun inject(mainActivity: MainActivity)

    fun inject(tabContainerFragment: TabContainerFragment)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun applicationContext(applicationContext: Context): Builder
        fun build(): AppComponent
    }

}