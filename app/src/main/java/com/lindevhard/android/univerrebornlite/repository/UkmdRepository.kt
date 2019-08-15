package com.lindevhard.android.univerrebornlite.repository

import com.lindevhard.android.univerrebornlite.api.UmkdApi
import retrofit2.Retrofit
import javax.inject.Inject

class UkmdRepository @Inject constructor(remoteClient: Retrofit) {
    private val ukmdService = remoteClient.create(UmkdApi::class.java)

    suspend fun getActualUkmdList() = ukmdService.getActualUkmdList()

    suspend fun getActualUmkdFiles(fieldId: Long) = ukmdService.getActualUmkdFiles(2019, 1, fieldId)

}