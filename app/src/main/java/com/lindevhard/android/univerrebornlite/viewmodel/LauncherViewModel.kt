package com.lindevhard.android.univerrebornlite.viewmodel

import android.net.wifi.WifiManager
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class LauncherViewModel @Inject constructor(val wm: WifiManager) : ViewModel()