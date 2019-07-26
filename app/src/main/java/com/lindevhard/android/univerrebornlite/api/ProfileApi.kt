package com.lindevhard.android.univerrebornlite.api

import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ProfileApi {
    @FormUrlEncoded
    @POST("/student/profile")
    suspend fun getUserProfile(): ApiResponse<ProfileData>
}