package com.lindevhard.android.univerrebornlite.viewmodel

import android.net.wifi.WifiManager
import androidx.lifecycle.ViewModel
import com.lindevhard.android.univerrebornlite.data.pref.PreferenceStorage
import javax.inject.Inject

class LauncherViewModel @Inject constructor(
        private val wm: WifiManager,
        private val preferenceStorage: PreferenceStorage) : ViewModel() {

    init {
        checkLoggedStatus()
    }

    fun checkLoggedStatus(): Boolean {
        return preferenceStorage.isLogged
    }
}