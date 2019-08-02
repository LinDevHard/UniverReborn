package com.lindevhard.android.univerrebornlite.api

import retrofit2.http.POST

interface SemesterApi {
    @POST("/student/semesterInfo")
    suspend fun getActualSemester(): ApiResponse<Semester>
}