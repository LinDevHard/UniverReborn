package com.lindevhard.android.univerrebornlite.repository

import android.util.Log
import com.lindevhard.android.univerrebornlite.api.*
import retrofit2.Retrofit
import javax.inject.Inject

class AuthRepository @Inject constructor(remoteClient: Retrofit,
                                         private val localDataSource: AuthLocalDataSource) {
    private val authService = remoteClient.create(AuthApi::class.java)

    suspend fun getAuthData(data: AuthDataServer): ApiResponse<AuthData> {
        val serverData = authService.getAuthData(data.password, data.login)
        Log.d("AuthRepository", data.toString())
        if (localDataSource.getAuthData().isEmpty()) {
            return when (serverData.code) {
                AuthStatus.INCORRECT_LOGIN -> serverData
                AuthStatus.AUTHORIZED -> {
                    localDataSource.insertAuthData(data)
                    serverData
                }
                AuthStatus.ALREADY_AUTHORIZED -> serverData
                else -> serverData
            }
        }
        return serverData
    }

    suspend fun retryAuth() {
        val authData = localDataSource.getAuthData()
        Log.d("retry auth", authData.toString())
        if (authData.isEmpty()) return
        getAuthData(authData[0])
    }

}