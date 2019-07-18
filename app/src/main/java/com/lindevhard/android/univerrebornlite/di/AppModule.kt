package com.lindevhard.android.univerrebornlite.di

import android.content.Context
import android.net.wifi.WifiManager
import com.lindevhard.android.univerrebornlite.App
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideContext(application: App): Context {
        return application.applicationContext
    }

    @Provides
    fun providesWifiManager(context: Context): WifiManager =
            context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
}