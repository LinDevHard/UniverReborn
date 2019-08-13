package com.lindevhard.android.univerrebornlite.api

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ExamScheduleApi {
    @POST("/student/examschedule")
    suspend fun getActualSchedule(): ApiResponse<ExamSchedule>

    @FormUrlEncoded
    @POST("/student/examschedule")
    suspend fun getScheduleBySemester(@Field("year") year: Int,
                                      @Field("semester") semester: Int): ApiResponse<ExamSchedule>

}