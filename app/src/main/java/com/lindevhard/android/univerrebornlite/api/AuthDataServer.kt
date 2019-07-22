package com.lindevhard.android.univerrebornlite.api

import com.google.gson.annotations.SerializedName

data class AuthDataServer(
        @SerializedName("login")
        val login: String,
        @SerializedName("password")
        val password: String
)