package com.lindevhard.android.univerrebornlite.repository

import com.lindevhard.android.univerrebornlite.api.Attendance
import com.lindevhard.android.univerrebornlite.api.AttendanceApi
import com.lindevhard.android.univerrebornlite.api.Semester
import com.lindevhard.android.univerrebornlite.api.SemesterApi
import com.lindevhard.android.univerrebornlite.data.DataSource.AttendanceLocalDataSource
import com.lindevhard.android.univerrebornlite.data.DataSource.SessionLocalDataSource
import com.lindevhard.android.univerrebornlite.data.model.Session
import retrofit2.Retrofit
import javax.inject.Inject

class AttendanceRepository @Inject constructor(remoteClient: Retrofit,
                                               private val localSource: AttendanceLocalDataSource,
                                               private val sessionSource: SessionLocalDataSource) {
    private val attendanceService = remoteClient.create(AttendanceApi::class.java)
    private val sessionService = remoteClient.create(SemesterApi::class.java)

    suspend fun getAttendance(): List<Attendance> {
        val session = getActualSession()
        if (session.code != 0) return attendanceService.getAttendance().data.subjectList

        val data = localSource.getAttendanceBySemester(session.data.currentYear, session.data.currentRk)
        if (data.isNotEmpty()) return data

        val newData = attendanceService.getAttendance()
        localSource.insertAttendance(session.data, newData.data.subjectList)
        return newData.data.subjectList
    }


    private suspend fun getActualSession() =
            sessionService.getActualSemester()

    suspend fun getActualSessionList(): List<Session> {
        val result = sessionSource.getSession()
        if (result.isNotEmpty())
            return result
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
        sessionSource.insertSession(sessionList)
        return sessionList
    }

    suspend fun getAttendanceBySemester(year: Int, semester: Int): List<Attendance> {
        val data = localSource.getAttendanceBySemester(year, semester)
        if (data.isNotEmpty())
            return data
        val newData = attendanceService.getAttendanceByYearSemester(year, semester).data.subjectList
        localSource.insertAttendance(Semester(0, 0, year, semester), newData)
        return newData
    }
}