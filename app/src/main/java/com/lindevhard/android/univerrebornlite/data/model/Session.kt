package com.lindevhard.android.univerrebornlite.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "session")
data class Session(
        val isCurrent: Boolean,
        val currentNumber: Int,
        val currentSemester: Int,
        val currentYear: Int
) {
    @PrimaryKey(autoGenerate = true)
    var sessionId: Long = 0
}