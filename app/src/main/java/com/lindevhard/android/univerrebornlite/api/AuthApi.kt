package com.lindevhard.android.univerrebornlite.api

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthApi {
    @FormUrlEncoded
    @POST("/user/login")
    suspend fun getAuthData(@Field("password") password: String,
                            @Field("login") login: String): ApiResponse<AuthData>
}