package com.lindevhard.android.univerrebornlite.api

import retrofit2.http.POST

interface AttendanceApi {
    @POST("/student/attendance")
    suspend fun getAttendance(): ApiResponse<AttendanceList>
}