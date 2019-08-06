package com.lindevhard.android.univerrebornlite.database.dao

import androidx.room.*
import com.lindevhard.android.univerrebornlite.api.AuthDataServer

@Dao
interface AuthDataDao {
    @Query("SELECT * FROM auth_data")
    suspend fun getAuthData(): List<AuthDataServer>

    @Insert
    suspend fun insertAuthData(authData: AuthDataServer)

    @Update
    suspend fun updateAuthData(authData: AuthDataServer)

    @Delete
    suspend fun deleteAuthData(authData: AuthDataServer)
}