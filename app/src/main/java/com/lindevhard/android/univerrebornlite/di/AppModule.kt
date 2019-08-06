package com.lindevhard.android.univerrebornlite.di

import android.content.Context
import android.content.SharedPreferences
import android.net.wifi.WifiManager
import android.preference.PreferenceManager
import com.lindevhard.android.univerrebornlite.App
import com.lindevhard.android.univerrebornlite.data.pref.PreferenceStorage
import com.lindevhard.android.univerrebornlite.data.pref.SharedPreferenceStorage
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    fun provideContext(application: App): Context {
        return application.applicationContext
    }

    @Singleton
    @Provides
    fun providesWifiManager(context: Context): WifiManager =
            context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager

    @Singleton
    @Provides
    fun providesSharedPreference(context: Context): SharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(context)

    @Singleton
    @Provides
    fun providesSharedPreferencesStorage(context: Context): PreferenceStorage =
            SharedPreferenceStorage(context)
}