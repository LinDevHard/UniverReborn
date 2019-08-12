package com.lindevhard.android.univerrebornlite.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lindevhard.android.univerrebornlite.api.AuthDataServer
import com.lindevhard.android.univerrebornlite.api.News
import com.lindevhard.android.univerrebornlite.api.ProfileData
import com.lindevhard.android.univerrebornlite.data.model.AttendanceDb
import com.lindevhard.android.univerrebornlite.data.model.Session
import com.lindevhard.android.univerrebornlite.database.dao.*

@Database(entities = [AuthDataServer::class, News::class, AttendanceDb::class, Session::class, ProfileData::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract val profileDao: ProfileDataDao
    abstract val sessionDao: SessionDataDao
    abstract val attendanceDao: AttendanceDataDao
    abstract val authDataDao: AuthDataDao
    abstract val newsDataDao: NewsDataDao
}