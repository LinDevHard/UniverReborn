package com.lindevhard.android.univerrebornlite.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lindevhard.android.univerrebornlite.data.model.Session

@Dao
interface SessionDataDao {
    @Query("SELECT * FROM session")
    suspend fun getSession(): List<Session>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSession(session: List<Session>)
}