package com.lindevhard.android.univerrebornlite.utils

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.lindevhard.android.univerrebornlite.api.Attendance
import com.lindevhard.android.univerrebornlite.api.AttendanceList
import java.lang.reflect.Type

class AttendanceDeserializer : JsonDeserializer<AttendanceList> {
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): AttendanceList {
        val rootJson = json.asJsonArray
        val data = mutableListOf<Attendance>()
        for (element in rootJson) {
            if (element.isJsonArray) {
                val subjectRoot = element.asJsonArray
                val attendData = Attendance()
                attendData.subjectName = subjectRoot[0].asJsonObject.get("name_1").asString
                attendData.type = subjectRoot[0].asJsonObject.get("name_2").asString
                attendData.period = subjectRoot[0].asJsonObject.get("name_3").asString
                attendData.groupId = subjectRoot[0].asJsonObject.get("groupInfo").asString

                attendData.firstRk = subjectRoot[1].asJsonObject.get("sum").asInt
                attendData.secondRk = subjectRoot[2].asJsonObject.get("sum").asInt
                data += attendData
            }
        }
        return AttendanceList(data)
    }
}
