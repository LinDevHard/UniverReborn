package com.lindevhard.android.univerrebornlite.repository

import com.lindevhard.android.univerrebornlite.api.AuthStatus
import com.lindevhard.android.univerrebornlite.api.News
import com.lindevhard.android.univerrebornlite.api.NewsApi
import com.lindevhard.android.univerrebornlite.api.NewsList
import com.lindevhard.android.univerrebornlite.data.DataSource.NewsLocalDataSource
import retrofit2.Retrofit
import javax.inject.Inject

class NewsRepository @Inject constructor(remoteClient: Retrofit,
                                         private val localSource: NewsLocalDataSource) {
    private lateinit var dataSource: List<News>
    private val newsService = remoteClient.create(NewsApi::class.java)

    suspend fun getNews(): List<News> {
        dataSource = localSource.getNewsAll()
        if (dataSource.isEmpty()) {
            val remoteSource = newsService.getNews()
            when (remoteSource.code) {
                AuthStatus.INCORRECT_LOGIN -> return remoteSource.data.news
                else -> saveDataInLocal(remoteSource.data)
            }
            return remoteSource.data.news
        }
        return dataSource
    }

    private suspend fun saveDataInLocal(newsList: NewsList) {
        newsList.news.map { localSource.insertNews(it) }
    }

    suspend fun getNewsById(id: Int): News {
        return localSource.getNewsById(id)
    }

}