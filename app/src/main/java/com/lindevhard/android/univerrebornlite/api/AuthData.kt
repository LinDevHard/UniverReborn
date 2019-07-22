package com.lindevhard.android.univerrebornlite.api

data class AuthData(
        val userAuthData: AuthDataServer,
        val lastLogin: String,
        val passChangeDate: String,
        val access: Long,
        val temppass: String,
        val infoTag: String,
        val _isCheater: Boolean,
        val ip: String,
        val loginTime: String,
        val userType: UserType,
        val name: String,
        val shortName: String,
        val status: Int
)