package com.lindevhard.android.univerrebornlite.repository

import com.lindevhard.android.univerrebornlite.api.ApiResponse
import com.lindevhard.android.univerrebornlite.api.ExamSchedule
import com.lindevhard.android.univerrebornlite.api.ExamScheduleApi
import com.lindevhard.android.univerrebornlite.api.SemesterApi
import com.lindevhard.android.univerrebornlite.data.DataSource.SessionLocalDataSource
import com.lindevhard.android.univerrebornlite.data.model.Session
import retrofit2.Retrofit
import javax.inject.Inject

class ExamScheduleRepository @Inject constructor(remoteClient: Retrofit,
                                                 private val sessionSource: SessionLocalDataSource) {
    private val examService = remoteClient.create(ExamScheduleApi::class.java)
    private val sessionService = remoteClient.create(SemesterApi::class.java)

    suspend fun getExamSchedule() = examService.getActualSchedule()

    suspend fun getExamSchedule(year: Int, semester: Int): ApiResponse<ExamSchedule> {
        return examService.getScheduleBySemester(year, semester)
    }

    private suspend fun getActualSession() =
            sessionService.getActualSemester()

    suspend fun getActualSessionList(): List<Session> {
        val result = sessionSource.getSession()
        if (result.isNotEmpty())
            return result
        val sessionList: MutableList<Session> = mutableListOf()
        val data = getActualSession()
        if (data.code == 0) {
            with(data.data) {
                for (sessionNum in 0 until count) {
                    val isCurrent = count - sessionNum == count
                    val newSession = Session(isCurrent, count - sessionNum, currentSemester, currentYear)
                    currentYear = if (currentSemester == 1) currentYear - 1 else currentYear
                    currentSemester = if (currentSemester == 1) 2 else 1
                    sessionList += newSession
                }
            }
        }
        sessionSource.insertSession(sessionList)
        return sessionList
    }

}
