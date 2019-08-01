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

        for (element in rootJson) {
            data += context.deserialize<News>(element, News::class.java)
        }

        /*  for (element in data)
              if (element.body.isEmpty())
                  data.remove(element)
          Log.d("NewsDeserializer", data.toString())
  */        return NewsList(data)
    }
}