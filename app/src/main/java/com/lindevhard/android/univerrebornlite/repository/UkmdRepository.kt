package com.lindevhard.android.univerrebornlite.repository

import android.util.Log
import com.lindevhard.android.univerrebornlite.api.UmkdApi
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class UkmdRepository @Inject constructor(remoteClient: Retrofit) {
    private val umkdService = remoteClient.create(UmkdApi::class.java)

    suspend fun getActualUkmdList() = umkdService.getActualUkmdList()

    suspend fun getActualUmkdFiles(fieldId: Long) = umkdService.getActualUmkdFiles(2019, 1, fieldId)

    suspend fun getUmkdFile(fileName: String, itemId: Long, subjectId: Long) {
        val data = umkdService.getUmkdFile(itemId, subjectId, 2019, 1)
        data.enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.e("DownLoad", "error")
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                Log.d("DownLoad", "server contacted and has file")
                writeBodyToDrive(response.body())
            }

        })
    }

    private fun writeBodyToDrive(body: ResponseBody?) {
        TODO("write saving file from Response")
    }

}