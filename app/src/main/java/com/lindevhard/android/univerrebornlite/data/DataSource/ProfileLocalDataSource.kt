package com.lindevhard.android.univerrebornlite.data.DataSource

import com.lindevhard.android.univerrebornlite.api.ProfileData
import com.lindevhard.android.univerrebornlite.database.dao.ProfileDataDao
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProfileLocalDataSource @Inject constructor(private val profileDataDao: ProfileDataDao) {

    suspend fun getProfileData(): List<ProfileData> {
        return withContext(IO) {
            profileDataDao.getProfileData()
        }
    }

    suspend fun insertProfileData(profileData: ProfileData) {
        withContext(IO) {
            profileDataDao.insertProfileData(profileData)
        }
    }
}