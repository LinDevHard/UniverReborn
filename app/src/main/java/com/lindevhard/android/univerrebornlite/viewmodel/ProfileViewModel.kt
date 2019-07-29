package com.lindevhard.android.univerrebornlite.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lindevhard.android.univerrebornlite.api.ProfileData
import com.lindevhard.android.univerrebornlite.repository.ProfileRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileViewModel @Inject constructor(private val repository: ProfileRepository) : ViewModel() {
    private val TAG = "ProfileViewModel"
    private val profileData = MutableLiveData<ProfileData>()
    val profile: LiveData<ProfileData> = profileData

    init {
    }

    fun updateProfile() {
        viewModelScope.launch {
            runCatching {
                repository.getUserProfile()
            }.onSuccess {
                if (it.code == 0) {
                    profileData.value = it.data
                } else {
                    Log.d(TAG, "Error")
                }
            }
        }
    }

}
