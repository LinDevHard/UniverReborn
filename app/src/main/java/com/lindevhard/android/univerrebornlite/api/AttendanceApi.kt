package com.lindevhard.android.univerrebornlite.api

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AttendanceApi {
    @FormUrlEncoded
    @POST("/student/attendance")
    suspend fun getAttendance(@Field("semester") semester: Int,
                              @Field("year") year: Int): ApiResponse<AttendanceList>
}