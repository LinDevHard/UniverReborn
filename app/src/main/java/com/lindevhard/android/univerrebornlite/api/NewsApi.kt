package com.lindevhard.android.univerrebornlite.api

import retrofit2.http.POST

interface NewsApi {
    @POST("/student/news")
    suspend fun getNews(): ApiResponse<News>
}