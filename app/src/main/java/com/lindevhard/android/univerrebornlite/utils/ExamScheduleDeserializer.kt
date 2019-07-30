package com.lindevhard.android.univerrebornlite.utils

import android.util.Log
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.lindevhard.android.univerrebornlite.api.Exam
import com.lindevhard.android.univerrebornlite.api.ExamSchedule
import java.lang.reflect.Type

class ExamScheduleDeserializer : JsonDeserializer<ExamSchedule> {
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): ExamSchedule {
        Log.d("ExamScheduleDeserialize", json.toString())
        val rootJson = json.asJsonArray
        val data = mutableListOf<Exam>()
        for (element in rootJson) {
            Log.d("ExamScheduleDeserialize", element.toString())
            data += context.deserialize<Exam>(element, Exam::class.java)
        }

        return ExamSchedule(data)
    }
}
