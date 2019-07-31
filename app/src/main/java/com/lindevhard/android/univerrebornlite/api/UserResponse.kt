package com.lindevhard.android.univerrebornlite.api

import com.google.gson.annotations.SerializedName

data class UserResponse<out T>(
        val code: Int = 0,
        val message: String,
        val userId: Long,
        val sessionId: String,
        @SerializedName("data")
        val data: List<T>
)