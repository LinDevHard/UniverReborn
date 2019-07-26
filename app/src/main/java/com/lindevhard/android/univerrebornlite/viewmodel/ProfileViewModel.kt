package com.lindevhard.android.univerrebornlite.viewmodel

import androidx.lifecycle.ViewModel
import com.lindevhard.android.univerrebornlite.repository.ProfileRepository
import javax.inject.Inject

class ProfileViewModel @Inject constructor(repository: ProfileRepository) : ViewModel()
