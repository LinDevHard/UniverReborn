package com.lindevhard.android.univerrebornlite.api

import com.google.gson.annotations.SerializedName

data class Semester(
        @SerializedName("semestersCount")
        val count: Int,
        val currentSemester: Int,
        val currentYear: Int,
        val currentRk: Int
)