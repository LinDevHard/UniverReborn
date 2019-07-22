package com.lindevhard.android.univerrebornlite.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lindevhard.android.univerrebornlite.api.AuthDataServer
import com.lindevhard.android.univerrebornlite.repository.AuthRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthViewModel @Inject constructor(val repository: AuthRepository) : ViewModel() {

    init {
        val data = AuthDataServer("test", "test")
        viewModelScope.launch {
            try {
                val response = repository.getAuthData(data = data)

                if (response.code != 0) {
                    Log.d("AuthViewModel", response.message)
                } else {
                    Log.d("AuthViewModel", response.message)
                }
            } catch (e: Exception) {
                Log.d("AuthViewModel", e.toString())
            }

        }
    }
}
