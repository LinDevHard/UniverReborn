package com.lindevhard.android.univerrebornlite.repository

import com.lindevhard.android.univerrebornlite.api.AuthApi
import com.lindevhard.android.univerrebornlite.api.AuthDataServer
import retrofit2.Retrofit
import javax.inject.Inject

class AuthRepository @Inject constructor(remoteClient: Retrofit) {
    private val authService = remoteClient.create(AuthApi::class.java)

    suspend fun getAuthData(data: AuthDataServer) = authService.getAuthData(data.password, data.login)

}