package com.lindevhard.android.univerrebornlite.di

import androidx.lifecycle.ViewModel
import com.lindevhard.android.univerrebornlite.ui.fragment.AuthFragment
import com.lindevhard.android.univerrebornlite.viewmodel.AuthViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class FragmentBuilderModule {
    @ContributesAndroidInjector
    internal abstract fun contibutesAuthFragment(): AuthFragment

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    internal abstract fun bindAuthViewModel(viewModel: AuthViewModel): ViewModel

}