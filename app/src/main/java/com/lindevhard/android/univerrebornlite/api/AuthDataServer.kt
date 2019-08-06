package com.lindevhard.android.univerrebornlite.api

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "auth_data")
data class AuthDataServer(
        @PrimaryKey
        @SerializedName("login")
        var login: String,
        @SerializedName("password")
        var password: String
)