package com.lindevhard.android.univerrebornlite.repository

import com.lindevhard.android.univerrebornlite.api.ProfileApi
import com.lindevhard.android.univerrebornlite.api.ProfileData
import com.lindevhard.android.univerrebornlite.data.DataSource.ProfileLocalDataSource
import retrofit2.Retrofit
import javax.inject.Inject

class ProfileRepository @Inject constructor(
        remoteClient: Retrofit,
        private val localSource: ProfileLocalDataSource
) {
    private val profileService = remoteClient.create(ProfileApi::class.java)

    suspend fun getUserProfile(): ProfileData {
        val result = localSource.getProfileData()
        if (result.isNotEmpty()) return result[0]

        val newResult = profileService.getUserProfile()
        if (newResult.code != 0) throw Exception()

        localSource.insertProfileData(newResult.data)
        return newResult.data
    }

}