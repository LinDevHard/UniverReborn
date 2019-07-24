package com.lindevhard.android.univerrebornlite.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lindevhard.android.univerrebornlite.api.AuthDataServer
import com.lindevhard.android.univerrebornlite.repository.AuthRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthViewModel @Inject constructor(private val repository: AuthRepository) : ViewModel() {
    private val TAG = "AuthViewModel"
    private var isProcess = MutableLiveData<Boolean>()
    private var isLogin = MutableLiveData<Boolean>()

    val progress: LiveData<Boolean> = isProcess
    val login: LiveData<Boolean> = isLogin

    init {
        //TODO: Create network checker
        isProcess.value = false
        isLogin.value = false
    }

    fun authRequests(login: String, password: String) {
        if (!isProcess.value!!) {
            Log.d("AuthViewModel", "Send Request")
            sendAuthRequest(login, password)
        }
    }

    private fun sendAuthRequest(login: String, password: String) {
        viewModelScope.launch {
            runCatching {
                isProcess.value = true
                repository.getAuthData(AuthDataServer(login, password))

            }.onSuccess {
                Log.d(TAG, "onSuccess")
                isLogin.value = true
            }.onFailure {
                Log.d(TAG, "onFailure")
                isLogin.value = false
            }
            isProcess.value = false
        }
    }
}
