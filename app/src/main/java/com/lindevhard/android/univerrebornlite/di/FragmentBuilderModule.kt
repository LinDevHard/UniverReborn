package com.lindevhard.android.univerrebornlite.di

import androidx.lifecycle.ViewModel
import com.lindevhard.android.univerrebornlite.ui.fragment.*
import com.lindevhard.android.univerrebornlite.viewmodel.*
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
    internal abstract fun contibutesExamScheduleFragment(): ExamScheduleFragment

    @ContributesAndroidInjector
    internal abstract fun contibutesNewsFragment(): NewsFragment

    @ContributesAndroidInjector
    internal abstract fun contibutesNewsDetailFragment(): NewsDetailFragment

    @ContributesAndroidInjector
    internal abstract fun contibutesAttendanceFragment(): AttendanceFragment

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

    @Binds
    @IntoMap
    @ViewModelKey(NewsViewModel::class)
    internal abstract fun bindNewsViewModel(viewModel: NewsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NewsDetailViewModel::class)
    internal abstract fun bindNewsDetailViewModel(viewModel: NewsDetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AttendanceViewModel::class)
    internal abstract fun bindAttendanceViewModel(viewModel: AttendanceViewModel): ViewModel
}