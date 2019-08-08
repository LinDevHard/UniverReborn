package com.lindevhard.android.univerrebornlite.api

import com.google.gson.annotations.SerializedName

data class ApiResponse<out T>(
        var code: Int = 0,
        val message: String,
        val userId: Long,
        val sessionId: String,
        @SerializedName("data")
        val data: T
)