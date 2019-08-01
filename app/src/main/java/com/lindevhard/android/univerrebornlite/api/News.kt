package com.lindevhard.android.univerrebornlite.api

data class NewsList(
        val news: List<News>
)

data class News(
        val title: String,
        val id: Int,
        val body: String,
        val date: String
)
