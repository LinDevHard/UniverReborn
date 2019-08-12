package com.lindevhard.android.univerrebornlite.di

import androidx.room.Room
import com.lindevhard.android.univerrebornlite.App
import com.lindevhard.android.univerrebornlite.data.DataSource.*
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

    @Provides
    @Singleton
    fun provideAttendanceDataSource(appDatabase: AppDatabase): AttendanceLocalDataSource =
            AttendanceLocalDataSource(appDatabase.attendanceDao)


    @Provides
    @Singleton
    fun provideSessionDataSource(appDatabase: AppDatabase): SessionLocalDataSource =
            SessionLocalDataSource(appDatabase.sessionDao)

    @Provides
    @Singleton
    fun provideProfileDataSource(appDatabase: AppDatabase): ProfileLocalDataSource =
            ProfileLocalDataSource(appDatabase.profileDao)
}