package com.lindevhard.android.univerrebornlite.api

import com.google.gson.annotations.SerializedName

data class UkmdList(
        val ukmdList: List<UkmdData>
)

data class UkmdData(
        @SerializedName("id")
        var ukmdId: Long,
        @SerializedName("sname")
        var sName: String,
        var type: String
)
