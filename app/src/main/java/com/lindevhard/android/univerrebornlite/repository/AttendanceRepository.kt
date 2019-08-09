package com.lindevhard.android.univerrebornlite.repository

import com.lindevhard.android.univerrebornlite.api.ApiResponse
import com.lindevhard.android.univerrebornlite.api.AttendanceApi
import com.lindevhard.android.univerrebornlite.api.AttendanceList
import com.lindevhard.android.univerrebornlite.api.SemesterApi
import com.lindevhard.android.univerrebornlite.data.model.Session
import retrofit2.Retrofit
import javax.inject.Inject

class AttendanceRepository @Inject constructor(remoteClient: Retrofit) {
    private val attendanceService = remoteClient.create(AttendanceApi::class.java)
    private val sessionService = remoteClient.create(SemesterApi::class.java)

    suspend fun getAttendance(): ApiResponse<AttendanceList> =
            attendanceService.getAttendance()

    private suspend fun getActualSession() =
            sessionService.getActualSemester()

    suspend fun getActualSessionList(): List<Session> {
        val sessionList: MutableList<Session> = mutableListOf()
        val data = getActualSession()
        if (data.code == 0){
            with(data.data){
                for ( sessionNum in 0 until count) {
                    val isCurrent = count-sessionNum == count
                    val newSession = Session(isCurrent,count - sessionNum, currentSemester, currentYear)
                    currentYear = if (currentSemester == 1) currentYear-1 else currentYear
                    currentSemester = if (currentSemester == 1) 2 else 1
                    sessionList += newSession
                }
            }
        }
        return sessionList
    }

    suspend fun getAttendanceBySemester(year: Int, semester: Int) =
            attendanceService.getAttendanceByYearSemester(year, semester)
}