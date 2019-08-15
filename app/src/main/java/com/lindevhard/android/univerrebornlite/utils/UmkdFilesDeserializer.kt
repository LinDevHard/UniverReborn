package com.lindevhard.android.univerrebornlite.utils

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.lindevhard.android.univerrebornlite.api.UmkdFiles
import com.lindevhard.android.univerrebornlite.api.UmkdFilesList
import java.lang.reflect.Type

class UmkdFilesDeserializer : JsonDeserializer<UmkdFilesList> {

    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): UmkdFilesList {
        val rootJsonArray = json.asJsonArray
        val data = mutableListOf<UmkdFiles>()

        for (element in rootJsonArray) {
            var owner: String = ""
            for ((i, el) in element.asJsonArray.withIndex()) {
                if (i == 0)
                    owner = el.asJsonObject.get("teacher").asString
                val subjectData = context.deserialize<UmkdFiles>(el, UmkdFiles::class.java)
                subjectData.owner = owner
                data += subjectData
            }
        }

        return UmkdFilesList(data.filter { it.itemId != 0L })
    }
}
