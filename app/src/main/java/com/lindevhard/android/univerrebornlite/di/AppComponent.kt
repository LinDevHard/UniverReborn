package com.lindevhard.android.univerrebornlite.di

import com.lindevhard.android.univerrebornlite.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            AndroidSupportInjectionModule::class,
            AppModule::class,
            ActivityBuilder::class,
            FragmentBuilderModule::class,
            ViewModelModule::class
        ]
)
interface AppComponent : AndroidInjector<App> {
    override fun inject(app: App)

    @Component.Builder
    interface Builder {
        fun build(): AppComponent

        @BindsInstance
        fun application(application: App): Builder
    }
}