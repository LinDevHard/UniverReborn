package com.lindevhard.android.univerrebornlite.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface UmkdApi {
    @POST("/student/umkd")
    suspend fun getActualUkmdList(): ApiResponse<UkmdList>

    @FormUrlEncoded
    @POST("/student/umkdFull")
    suspend fun getActualUmkdFiles(@Field("year") year: Int,
                                   @Field("semester") semester: Int,
                                   @Field("subjectId") id: Long): ApiResponse<UmkdFilesList>

    @GET("/student/umkd/get/{itemId}/{subjectId}/{year}/{semester}")
    @Streaming
    suspend fun getUmkdFile(@Path("itemId") itemId: Long,
                            @Path("subjectId") subjectId: Long,
                            @Path("year") year: Int,
                            @Path("semester") semester: Int): Call<ResponseBody>

}