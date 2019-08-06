package com.lindevhard.android.univerrebornlite

import com.lindevhard.android.univerrebornlite.api.AuthDataServer
import com.lindevhard.android.univerrebornlite.repository.AuthLocalDataSource
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import javax.inject.Inject

class AuthDaoTest @Inject constructor(private val localDataSource: AuthLocalDataSource) {


    @Test
    fun insertAndGetAuthData() = runBlocking {
        val testData = AuthDataServer("test", "test")
        localDataSource.insertAuthData(testData)

        val authDataFromDb = localDataSource.getAuthData()
        assertEquals(listOf(testData), authDataFromDb)
    }
}