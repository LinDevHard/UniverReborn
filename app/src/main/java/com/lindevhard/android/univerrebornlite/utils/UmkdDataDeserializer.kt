package com.lindevhard.android.univerrebornlite.utils

import android.util.Log
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.lindevhard.android.univerrebornlite.api.UkmdData
import com.lindevhard.android.univerrebornlite.api.UkmdList
import java.lang.reflect.Type

class UmkdDataDeserializer : JsonDeserializer<UkmdList> {
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): UkmdList {
        val rootJsonArray = json.asJsonArray
        val data = mutableListOf<UkmdData>()
        if (rootJsonArray.size() <= 1) {
            Log.d("UKMD Schedule DESERL", rootJsonArray.toString())

            return UkmdList(data)
        }

        for (element in rootJsonArray) {
            data += context.deserialize<UkmdData>(element, UkmdData::class.java)
        }

        return UkmdList(data)
    }
}
