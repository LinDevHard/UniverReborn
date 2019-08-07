package com.lindevhard.android.univerrebornlite.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lindevhard.android.univerrebornlite.api.AuthDataServer
import com.lindevhard.android.univerrebornlite.api.News
import com.lindevhard.android.univerrebornlite.database.dao.AuthDataDao
import com.lindevhard.android.univerrebornlite.database.dao.NewsDataDao

@Database(entities = [AuthDataServer::class, News::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract val authDataDao: AuthDataDao
    abstract val newsDataDao: NewsDataDao
}