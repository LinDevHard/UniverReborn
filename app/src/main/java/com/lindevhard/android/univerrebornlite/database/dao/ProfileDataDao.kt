package com.lindevhard.android.univerrebornlite.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lindevhard.android.univerrebornlite.api.ProfileData

@Dao
interface ProfileDataDao {
    @Query("SELECT * FROM profile")
    suspend fun getProfileData(): List<ProfileData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProfileData(profileData: ProfileData)
}