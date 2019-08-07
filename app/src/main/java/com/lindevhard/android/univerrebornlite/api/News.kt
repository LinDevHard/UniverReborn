package com.lindevhard.android.univerrebornlite.api

import androidx.room.Entity
import androidx.room.PrimaryKey

data class NewsList(
        val news: List<News>
)

@Entity(tableName = "news")
data class News(
        val title: String,
        @PrimaryKey
        val id: Int,
        val body: String,
        val date: String
)
