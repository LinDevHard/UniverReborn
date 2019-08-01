package com.lindevhard.android.univerrebornlite.utils

import android.util.Log
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.lindevhard.android.univerrebornlite.api.News
import com.lindevhard.android.univerrebornlite.api.NewsList
import java.lang.reflect.Type

class NewsDeserializer : JsonDeserializer<NewsList> {
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): NewsList {
        Log.d("NewsDeserializer", json.toString())
        val rootJson = json.asJsonArray
        val data = mutableListOf<News>()

        rootJson.map { data += deserializeNews(it, context) }

        return NewsList(data.filter { it.body.isNotEmpty() })
    }

    private fun deserializeNews(jsonElement: JsonElement, context: JsonDeserializationContext) =
            context.deserialize<News>(jsonElement, News::class.java)

}