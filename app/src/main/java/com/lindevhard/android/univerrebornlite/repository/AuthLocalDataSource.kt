package com.lindevhard.android.univerrebornlite.repository

import android.util.Log
import com.lindevhard.android.univerrebornlite.api.AuthDataServer
import com.lindevhard.android.univerrebornlite.database.dao.AuthDataDao
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthLocalDataSource @Inject constructor(private val authDataDao: AuthDataDao) {
    suspend fun getAuthData(): List<AuthDataServer> {
        return withContext(IO) {
            Log.d("AuthDao", "getAuthData")
            authDataDao.getAuthData()
        }
    }

    suspend fun insertAuthData(authData: AuthDataServer) {
        withContext(IO) {
            Log.d("AuthDao", "insertAuthData")

            authDataDao.insertAuthData(authData)
        }
    }

    suspend fun deleteAuthData(authData: AuthDataServer) {
        withContext(IO) {
            authDataDao.deleteAuthData(authData)
        }
    }

    suspend fun updateAuthData(authData: AuthDataServer) {
        withContext(IO) {
            authDataDao.updateAuthData(authData)
        }
    }
}