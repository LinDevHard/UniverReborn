package com.lindevhard.android.univerrebornlite.di

import androidx.room.Room
import com.lindevhard.android.univerrebornlite.App
import com.lindevhard.android.univerrebornlite.data.DataSource.AuthLocalDataSource
import com.lindevhard.android.univerrebornlite.data.DataSource.NewsLocalDataSource
import com.lindevhard.android.univerrebornlite.database.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(application: App): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "localData").build()
    }

    @Provides
    @Singleton
    fun provideAuthDataSource(appDatabase: AppDatabase): AuthLocalDataSource =
            AuthLocalDataSource(appDatabase.authDataDao)

    @Provides
    @Singleton
    fun provideNewsDataSource(appDatabase: AppDatabase): NewsLocalDataSource =
            NewsLocalDataSource(appDatabase.newsDataDao)
}