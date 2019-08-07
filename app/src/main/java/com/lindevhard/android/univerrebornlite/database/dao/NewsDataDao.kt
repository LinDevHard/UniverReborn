package com.lindevhard.android.univerrebornlite.database.dao

import androidx.room.*
import com.lindevhard.android.univerrebornlite.api.News

@Dao
interface NewsDataDao {
    @Query("SELECT * FROM news")
    fun getNewsAll(): List<News>

    @Query("SELECT * FROM news WHERE id=:id")
    fun getNewsById(id: Int): News

    @Update
    fun updateNews(news: News)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNews(news: News)
}