package com.lindevhard.android.univerrebornlite.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lindevhard.android.univerrebornlite.api.AuthDataServer
import com.lindevhard.android.univerrebornlite.database.dao.AuthDataDao

@Database(entities = [AuthDataServer::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract val authDataDao: AuthDataDao
}