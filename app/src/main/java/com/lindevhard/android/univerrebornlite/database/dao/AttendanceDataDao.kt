package com.lindevhard.android.univerrebornlite.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lindevhard.android.univerrebornlite.api.Attendance
import com.lindevhard.android.univerrebornlite.data.model.AttendanceDb

@Dao
interface AttendanceDataDao {

    @Query("SELECT * FROM attendance")
    suspend fun getAttendanceAll(): List<Attendance>

    @Query("SELECT * FROM attendance WHERE currentYear=:year and currentRk=:semester")
    suspend fun getAttendanceBySemester(year: Int, semester: Int): List<Attendance>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAttendanceData(attendance: List<AttendanceDb>)

}