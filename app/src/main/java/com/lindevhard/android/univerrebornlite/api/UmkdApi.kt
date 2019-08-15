package com.lindevhard.android.univerrebornlite.api

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface UmkdApi {
    @POST("/student/umkd")
    suspend fun getActualUkmdList(): ApiResponse<UkmdList>

    @FormUrlEncoded
    @POST("/student/umkdFull")
    suspend fun getActualUmkdFiles(@Field("year") year: Int,
                                   @Field("semester") semester: Int,
                                   @Field("subjectId") id: Long): ApiResponse<UmkdFilesList>

}