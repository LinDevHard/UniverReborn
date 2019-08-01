package com.lindevhard.android.univerrebornlite.repository

import com.lindevhard.android.univerrebornlite.api.News
import com.lindevhard.android.univerrebornlite.api.NewsApi
import retrofit2.Retrofit
import javax.inject.Inject

class NewsRepository @Inject constructor(remoteClient: Retrofit) {
    private val newsService = remoteClient.create(NewsApi::class.java)

    suspend fun getNews() = newsService.getNews()

    suspend fun getNewsById(id: Int): News? {
        val response = getNews()
        if (response.code != 0)
            return null

        for (elem in response.data.news) if (elem.id == id) {
            return elem
        }
        return null
    }

}
