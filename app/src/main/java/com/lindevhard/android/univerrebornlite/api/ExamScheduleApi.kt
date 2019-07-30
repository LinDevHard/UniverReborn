package com.lindevhard.android.univerrebornlite.api

import retrofit2.http.POST

interface ExamScheduleApi {
    @POST("/student/examschedule")
    suspend fun getExamSchedule(): ApiResponse<ExamSchedule>
}