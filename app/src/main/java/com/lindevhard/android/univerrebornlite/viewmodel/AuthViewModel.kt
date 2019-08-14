package com.lindevhard.android.univerrebornlite.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lindevhard.android.univerrebornlite.api.AuthDataServer
import com.lindevhard.android.univerrebornlite.data.pref.PreferenceStorage
import com.lindevhard.android.univerrebornlite.repository.AuthRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthViewModel @Inject constructor(
        private val repository: AuthRepository,
        private val preferenceStorage: PreferenceStorage) : ViewModel() {

    private val TAG = "AuthViewModel"
    private var isProcess = MutableLiveData<Boolean>()
    private var _isLogin = MutableLiveData<Boolean>()

    val progress: LiveData<Boolean> = isProcess
    val isLogin: LiveData<Boolean> = _isLogin


    init {

    }

    fun authRequest(login: String, password: String) {
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
            }.onFailure {
                _isLogin.value = false
                Log.d("AuthViewModel", it.message)
            }
            isProcess.value = false
            preferenceStorage.isLogged = _isLogin.value!!
        }
    }

    companion object {
        const val AUTHORIZED = 0
        const val ALREADY_AUTHORIZED = 1
        const val INCORRECT_LOGIN = 2
    }
}
