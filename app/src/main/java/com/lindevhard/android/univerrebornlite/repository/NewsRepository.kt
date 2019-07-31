package com.lindevhard.android.univerrebornlite.repository

import com.lindevhard.android.univerrebornlite.api.NewsApi
import retrofit2.Retrofit
import javax.inject.Inject

class NewsRepository @Inject constructor(private val remoteClient: Retrofit) {
    private val examService = remoteClient.create(NewsApi::class.java)

    suspend fun getNews() = examService.getNews()

}
