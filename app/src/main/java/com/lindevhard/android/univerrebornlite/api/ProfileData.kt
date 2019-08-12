package com.lindevhard.android.univerrebornlite.api

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profile")
data class ProfileData(
        @Embedded
        val univerData: UniverData,
        @Embedded
        val personalData: PersonalData
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L
}

data class UniverData(
        var student: String = "",
        var eduForm: String = "",
        var eduLevel: String = "",
        var langDiv: String = "",
        var stage: String = "",
        var faculty: String = "",
        var speciality: String = "",
        var forLang: String = "",
        var courseNum: String = "",
        var grantDate: String = "",
        var grantNum: String = "",
        var status: String = ""
)

data class PersonalData(
        var surname: String = "",
        var name: String = "",
        var patronymic: String = "",
        var gradeBookNumber: String = "",
        var sex: String = "",
        var studentId: String = ""
)