package com.lindevhard.android.univerrebornlite.repository

import com.lindevhard.android.univerrebornlite.api.ExamScheduleApi
import retrofit2.Retrofit
import javax.inject.Inject

class ExamScheduleRepository @Inject constructor(remoteClient: Retrofit) {
    private val examService = remoteClient.create(ExamScheduleApi::class.java)

    suspend fun getExamSchedule() = examService.getExamSchedule()

}
