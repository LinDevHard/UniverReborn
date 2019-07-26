package com.lindevhard.android.univerrebornlite.repository

import com.lindevhard.android.univerrebornlite.api.AuthDataServer
import com.lindevhard.android.univerrebornlite.api.ProfileApi
import retrofit2.Retrofit
import javax.inject.Inject

class ProfileRepository @Inject constructor(
        remoteClient: Retrofit
) {
    private val authService = remoteClient.create(ProfileApi::class.java)

    suspend fun getUserProfile(data: AuthDataServer) = authService.getUserProfile()

}