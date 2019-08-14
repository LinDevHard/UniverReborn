package com.lindevhard.android.univerrebornlite.api

data class UkmdList(
        val ukmdList: List<UkmdData>
)

data class UkmdData(
        var ukmdId: Long,
        var sName: String,
        var type: String
)
