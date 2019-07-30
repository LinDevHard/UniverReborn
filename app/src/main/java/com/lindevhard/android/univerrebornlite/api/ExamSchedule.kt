package com.lindevhard.android.univerrebornlite.api

import com.google.gson.annotations.SerializedName

data class ExamSchedule(
        val examList: List<Exam>
)

data class Exam(
        @SerializedName("audience")
        val audience: String = "",
        @SerializedName("audienceFaculty")
        val audienceFaculty: String = "",
        @SerializedName("date")
        val date: String = "",
        @SerializedName("subject")
        val subject: String = "",
        @SerializedName("teacher")
        val teacher: String = "",
        @SerializedName("time")
        val time: String = "",
        @SerializedName("type")
        val type: String = ""
)
