package com.lindevhard.android.univerrebornlite.data.DataSource

import com.lindevhard.android.univerrebornlite.api.News
import com.lindevhard.android.univerrebornlite.database.dao.NewsDataDao
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsLocalDataSource @Inject constructor(private val newsDataDao: NewsDataDao) {
    suspend fun getNewsAll(): List<News> {
        return withContext(IO) {
            newsDataDao.getNewsAll()
        }
    }

    suspend fun getNewsById(id: Int): News {
        return withContext(IO) {
            newsDataDao.getNewsById(id)
        }
    }

    suspend fun insertNews(news: News) {
        withContext(IO) {
            newsDataDao.insertNews(news)
        }
    }

    suspend fun updateNews(news: News) {
        withContext(IO) {
            newsDataDao.updateNews(news)
        }
    }
}