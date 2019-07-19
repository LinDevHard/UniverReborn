package com.lindevhard.android.univerrebornlite.utils

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

/**
 * For Activities, allows declarations like
 * ```
 * val myViewModel = viewModelProvider(myViewModelFactory)
 * ```
 */
inline fun <reified VM : ViewModel> FragmentActivity.viewModelProvider(
        provider: ViewModelProvider.Factory
) = ViewModelProviders.of(this, provider).get(VM::class.java)
