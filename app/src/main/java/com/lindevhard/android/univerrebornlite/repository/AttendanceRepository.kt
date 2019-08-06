package com.lindevhard.android.univerrebornlite.repository

import android.util.Log
import com.lindevhard.android.univerrebornlite.api.ApiResponse
import com.lindevhard.android.univerrebornlite.api.AttendanceApi
import com.lindevhard.android.univerrebornlite.api.AttendanceList
import com.lindevhard.android.univerrebornlite.api.AuthApi
import retrofit2.Retrofit
import javax.inject.Inject

class AttendanceRepository @Inject constructor(remoteClient: Retrofit,
                                               private val authRepository: AuthRepository) {
    private val attendanceService = remoteClient.create(AttendanceApi::class.java)
    private val authService = remoteClient.create(AuthApi::class.java)

    suspend fun getAttendance(): ApiResponse<AttendanceList> {
        val attendanceResponse = attendanceService.getAttendance(2, 2018)
        Log.d("attendanceRepo", attendanceResponse.toString())
        return attendanceResponse
    }

}