package com.lindevhard.android.univerrebornlite.api

import retrofit2.http.POST

interface ProfileApi {
    @POST("/student/profile")
    suspend fun getUserProfile(): ApiResponse<ProfileData>
}