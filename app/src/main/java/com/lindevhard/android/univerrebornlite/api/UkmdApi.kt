package com.lindevhard.android.univerrebornlite.api

import retrofit2.http.POST

interface UkmdApi {
    @POST("/student/ukmd")
    suspend fun getActualUkmdList(): ApiResponse<UkmdList>
}