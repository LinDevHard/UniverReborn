package com.lindevhard.android.univerrebornlite.di

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import dagger.Binds
import dagger.Module

/**
 * Builder for activity.
 */
@Module
abstract class ActivityModule {

    @Binds
    internal abstract fun provideActivity(activity: AppCompatActivity): Activity

    @Binds
    internal abstract fun provideLifecycleOwner(activity: AppCompatActivity): LifecycleOwner


}
