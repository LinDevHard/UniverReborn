package com.lindevhard.android.univerrebornlite.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lindevhard.android.univerrebornlite.api.Semester

@Entity(tableName = "attendance")
data class AttendanceDb(
        var subjectName: String = "",
        var type: String = "",
        var period: String = "",
        var groupId: String = "",
        var firstRk: Int = 0,
        var secondRk: Int = 0,
        @Embedded
        var session: Semester
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}