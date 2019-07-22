package com.lindevhard.android.univerrebornlite.di

import androidx.lifecycle.ViewModel
import com.lindevhard.android.univerrebornlite.ui.activity.MainActivity
import com.lindevhard.android.univerrebornlite.viewmodel.LauncherViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class ActivityBuilder {

    @ActivityScope
    @ContributesAndroidInjector(
            modules = [ActivityModule::class])
    internal abstract fun contributeMainActivity(): MainActivity

    @Binds
    @IntoMap
    @ViewModelKey(LauncherViewModel::class)
    internal abstract fun bindAuthViewModel(viewModel: LauncherViewModel): ViewModel
}
