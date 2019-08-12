package com.lindevhard.android.univerrebornlite.data.DataSource

import com.lindevhard.android.univerrebornlite.api.Attendance
import com.lindevhard.android.univerrebornlite.api.Semester
import com.lindevhard.android.univerrebornlite.data.model.AttendanceDb
import com.lindevhard.android.univerrebornlite.database.dao.AttendanceDataDao
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AttendanceLocalDataSource @Inject constructor(private val attendanceDataDao: AttendanceDataDao) {

    suspend fun getAttendanceAll(): List<Attendance> {
        return withContext(IO) {
            attendanceDataDao.getAttendanceAll()
        }
    }

    suspend fun getAttendanceBySemester(year: Int, semester: Int): List<Attendance> {
        return withContext(IO) {
            attendanceDataDao.getAttendanceBySemester(year, semester)
        }
    }

    suspend fun insertAttendance(session: Semester, newData: List<Attendance>) {
        withContext(IO) {
            val data: MutableList<AttendanceDb> = mutableListOf()
            newData.map {
                data += AttendanceDb(
                        it.subjectName,
                        it.type,
                        it.period,
                        it.groupId,
                        it.firstRk,
                        it.secondRk,
                        session)
            }
            attendanceDataDao.insertAttendanceData(data)
        }
    }
}