package com.lindevhard.android.univerrebornlite.api

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AttendanceApi {
    @FormUrlEncoded
    @POST("/student/attendance")
    suspend fun getAttendanceByYearSemester(@Field("year") year: Int,
                                            @Field("semester") semester: Int): ApiResponse<AttendanceList>

    @POST("/student/attendance")
    suspend fun getAttendance(): ApiResponse<AttendanceList>
}