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
    private var _isLogin = MutableLiveData<Boolean>()

    val progress: LiveData<Boolean> = isProcess
    val isLogin: LiveData<Boolean> = _isLogin

    init {
        //TODO: Create network checker

    }

    fun authRequest(login: String, password: String) {
            Log.d("AuthViewModel", "Send Request")
            sendAuthRequest(login, password)
    }

    private fun sendAuthRequest(login: String, password: String) {
        viewModelScope.launch {
            runCatching {
                isProcess.value = true
                repository.getAuthData(AuthDataServer(login, password))
            }.onSuccess {
                when (it.code) {
                    AUTHORIZED -> _isLogin.value = true
                    ALREADY_AUTHORIZED -> _isLogin.value = true
                    INCORRECT_LOGIN -> _isLogin.value = false
                }
                Log.d("AuthViewModel", it.toString())
            }.onFailure {
                _isLogin.value = false
            }
            isProcess.value = false
        }
    }

    companion object {
        const val AUTHORIZED = 0
        const val ALREADY_AUTHORIZED = 1
        const val INCORRECT_LOGIN = 2
    }

}
