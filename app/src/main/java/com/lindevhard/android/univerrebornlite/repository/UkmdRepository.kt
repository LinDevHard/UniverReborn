package com.lindevhard.android.univerrebornlite.repository

import com.lindevhard.android.univerrebornlite.api.UkmdApi
import retrofit2.Retrofit
import javax.inject.Inject

class UkmdRepository @Inject constructor(remoteClient: Retrofit) {
    private val ukmdService = remoteClient.create(UkmdApi::class.java)

    suspend fun getActualUkmdList() = ukmdService.getActualUkmdList()

}