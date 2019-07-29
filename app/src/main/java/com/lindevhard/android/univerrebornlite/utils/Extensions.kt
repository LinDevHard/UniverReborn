package com.lindevhard.android.univerrebornlite.utils

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
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

/**
 * For Fragments, allows declarations like
 * ```
 * val myViewModel = viewModelProvider(myViewModelFactory)
 * ```
 */
inline fun <reified VM : ViewModel> Fragment.viewModelProvider(
        provider: ViewModelProvider.Factory
) = ViewModelProviders.of(this, provider).get(VM::class.java)

/**
 * For Fragments, show toast error messages
 *  ```
 *  showError(R.string.error_message)
 *  ```
 */
fun Fragment.showError(@StringRes stringId: Int) {
    Toast.makeText(this.context, stringId, Toast.LENGTH_LONG).show()
}

fun String.split(): String {
    val index = this.indexOf(":")
    if (index == -1)
        return this
    return this.substring(index + 1)
}