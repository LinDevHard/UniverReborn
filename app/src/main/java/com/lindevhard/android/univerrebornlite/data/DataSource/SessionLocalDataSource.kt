package com.lindevhard.android.univerrebornlite.data.DataSource

import com.lindevhard.android.univerrebornlite.data.model.Session
import com.lindevhard.android.univerrebornlite.database.dao.SessionDataDao
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SessionLocalDataSource @Inject constructor(private val sessionDataDao: SessionDataDao) {
    suspend fun getSession(): List<Session> {
        return withContext(IO) {
            sessionDataDao.getSession()
        }
    }

    suspend fun insertSession(session: List<Session>) {
        withContext(IO) {
            sessionDataDao.insertSession(session)
        }
    }
}