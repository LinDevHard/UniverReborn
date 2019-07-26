package com.lindevhard.android.univerrebornlite.api

//TODO: Refactor var names
data class ProfileData(
        val data: List<UserUniverData>
)

data class UserUniverData(
        val unidata: List<UniverData>,
        val userdata: List<UserData>
)

data class UniverData(
        val student: String,
        val edu_form: String,
        val stage: String,
        val faculty: String,
        val speciality: String,
        val payment_form: String,
        val course_num: String,
        val status: String
)

data class UserData(
        val sname: String,
        val name: String,
        val fname: String,
        val zachetka: String,
        val sex: String
)
