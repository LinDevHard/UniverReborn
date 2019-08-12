package com.lindevhard.android.univerrebornlite.api

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class Semester(
        @SerializedName("semestersCount")
        val count: Int,
        var currentSemester: Int,
        var currentYear: Int,
        val currentRk: Int
)