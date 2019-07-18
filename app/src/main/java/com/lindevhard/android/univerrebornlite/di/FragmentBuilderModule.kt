package com.lindevhard.android.univerrebornlite.di

import com.lindevhard.android.univerrebornlite.ui.fragment.AuthFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {
    @ContributesAndroidInjector
    internal abstract fun contibutesAuthFragment(): AuthFragment
}