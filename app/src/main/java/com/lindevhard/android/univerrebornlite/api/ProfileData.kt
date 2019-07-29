package com.lindevhard.android.univerrebornlite.api

data class ProfileData(
        val univerData: UniverData,
        val personalData: PersonalData
)

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