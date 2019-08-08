package com.lindevhard.android.univerrebornlite.utils

import android.content.SharedPreferences
import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException


class AddCookiesInterceptor(private val prefer: SharedPreferences) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()

        val preferences = prefer.getStringSet(PREF_COOKIES, HashSet()) as HashSet<String>

        preferences.map { cookie -> builder.addHeader("Cookie", cookie) }

        return chain.proceed(builder.build())
    }

    companion object {
        const val PREF_COOKIES = "PREF_COOKIES"
    }
}

class ReceivedCookiesInterceptor(private val prefer: SharedPreferences) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalResponse = chain.proceed(chain.request())
        if (originalResponse.headers("Set-Cookie").isNotEmpty()) {
            val cookies = prefer.getStringSet(PREF_COOKIES, HashSet()) as HashSet<String>
            originalResponse.headers("Set-Cookie").map { header -> cookies.add(header) }


            val memes = prefer.edit()
            memes.putStringSet("PREF_COOKIES", cookies).apply()
            memes.commit()
            Log.d("Cookie interceptor", "memes commit")
        }

        return originalResponse
    }

    companion object {
        const val PREF_COOKIES = "PREF_COOKIES"
    }
}