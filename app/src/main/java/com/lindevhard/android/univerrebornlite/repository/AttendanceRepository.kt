package com.lindevhard.android.univerrebornlite.repository

import com.lindevhard.android.univerrebornlite.api.AttendanceApi
import retrofit2.Retrofit
import javax.inject.Inject

class AttendanceRepository @Inject constructor(remoteClient: Retrofit) {
    private val attendanceService = remoteClient.create(AttendanceApi::class.java)

    suspend fun getAttendance() = attendanceService.getAttendance()

}