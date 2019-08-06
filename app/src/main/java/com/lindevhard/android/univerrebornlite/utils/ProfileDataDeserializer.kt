package com.lindevhard.android.univerrebornlite.utils

import android.util.Log
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.lindevhard.android.univerrebornlite.api.PersonalData
import com.lindevhard.android.univerrebornlite.api.ProfileData
import com.lindevhard.android.univerrebornlite.api.UniverData
import java.lang.reflect.Type

class ProfileDataDeserializer : JsonDeserializer<ProfileData> {
    override fun deserialize(json: JsonElement, typeOfT: Type?, context: JsonDeserializationContext?): ProfileData {
        val rootJsonArray = json.asJsonArray

        val personalData = PersonalData()
        val univerData = UniverData()
        if (rootJsonArray.size() <= 1) {
            Log.d("ProfileDeserializer", rootJsonArray.toString())
            return ProfileData(univerData, personalData)
        }
        if (rootJsonArray[0].isJsonArray) {

            val univerJsonArray = rootJsonArray[0].asJsonArray
            univerData.student = univerJsonArray[0].asJsonObject.get("student").asString.split()
            univerData.eduForm = univerJsonArray[1].asJsonObject.get("edu_form").asString.split()
            univerData.eduLevel = univerJsonArray[2].asJsonObject.get("edu_level").asString.split()
            univerData.langDiv = univerJsonArray[3].asJsonObject.get("lang_div").asString.split()
            univerData.stage = univerJsonArray[4].asJsonObject.get("stage").asString.split()
            univerData.faculty = univerJsonArray[5].asJsonObject.get("faculty").asString.split()
            univerData.speciality = univerJsonArray[6].asJsonObject.get("speciality").asString.split()
            univerData.forLang = univerJsonArray[8].asJsonObject.get("for_lang").asString.split()
            univerData.courseNum = univerJsonArray[9].asJsonObject.get("course_num").asString.split()
            univerData.grantDate = univerJsonArray[11].asJsonObject.get("grant_date").asString.split()
            univerData.grantNum = univerJsonArray[12].asJsonObject.get("grant_num").asString.split()
            univerData.status = univerJsonArray[17].asJsonObject.get("status").asString.split()
        }
        if (rootJsonArray[1].isJsonArray) {

            val univerJsonArray = rootJsonArray[1].asJsonArray
            personalData.surname = univerJsonArray[0].asJsonObject.get("sname").asString.split()
            personalData.name = univerJsonArray[1].asJsonObject.get("name").asString.split()
            personalData.patronymic = univerJsonArray[2].asJsonObject.get("fname").asString.split()
            personalData.gradeBookNumber = univerJsonArray[3].asJsonObject.get("zachetka").asString.split()
            personalData.sex = univerJsonArray[4].asJsonObject.get("sex").asString.split()
            personalData.studentId = univerJsonArray[5].asJsonObject.get("studentId").asString.split()
        }
        return ProfileData(univerData, personalData)
    }
}