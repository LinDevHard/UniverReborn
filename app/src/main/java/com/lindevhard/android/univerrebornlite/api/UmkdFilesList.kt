package com.lindevhard.android.univerrebornlite.api

data class UmkdFilesList(
        var umkdList: List<UmkdFiles>
)

data class UmkdFiles(
        val itemId: Long = 0L,
        val fname: String,
        val type: String,
        val size: String,
        var owner: String
)
