package com.lindevhard.android.univerrebornlite.di

import androidx.lifecycle.ViewModel
import com.lindevhard.android.univerrebornlite.ui.fragment.AuthFragment
import com.lindevhard.android.univerrebornlite.ui.fragment.ExamScheduleFragment
import com.lindevhard.android.univerrebornlite.ui.fragment.ProfileFragment
import com.lindevhard.android.univerrebornlite.viewmodel.AuthViewModel
import com.lindevhard.android.univerrebornlite.viewmodel.ExamScheduleViewModel
import com.lindevhard.android.univerrebornlite.viewmodel.ProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class FragmentBuilderModule {
    @ContributesAndroidInjector
    internal abstract fun contibutesAuthFragment(): AuthFragment

    @ContributesAndroidInjector
    internal abstract fun contibutesProfileFragment(): ProfileFragment

    @ContributesAndroidInjector
    internal abstract fun contibutesExamScheduleViewModel(): ExamScheduleFragment

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    internal abstract fun bindAuthViewModel(viewModel: AuthViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    internal abstract fun bindProfileViewModel(viewModel: ProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ExamScheduleViewModel::class)
    internal abstract fun bindExamScheduleViewModel(viewModel: ExamScheduleViewModel): ViewModel
}