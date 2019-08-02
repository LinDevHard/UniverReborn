package com.lindevhard.android.univerrebornlite.api

data class AttendanceList(
        val subjectList: List<Attendance>
)

data class Attendance(
        var subjectName: String = "",
        var type: String = "",
        var period: String = "",
        var groupId: String = "",
        var firstRk: Int = 0,
        var secondRk: Int = 0

)