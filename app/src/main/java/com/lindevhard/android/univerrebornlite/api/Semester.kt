package com.lindevhard.android.univerrebornlite.api

import com.google.gson.annotations.SerializedName

data class Semester(
        @SerializedName("semestersCount")
        val count: Int,
        var currentSemester: Int,
        var currentYear: Int,
        val currentRk: Int
)