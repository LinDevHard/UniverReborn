package com.lindevhard.android.univerrebornlite.utils

import com.lindevhard.android.univerrebornlite.data.DataSource.AuthLocalDataSource
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import okhttp3.FormBody
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject


class AuthorizationInterceptor @Inject constructor(private val authLocalDataSource: AuthLocalDataSource) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originUrl = chain.request().url()
        val originResponse = chain.proceed(chain.request())

        if (originResponse.code() == 213) {
            var actualHeaders: List<String> = listOf()
            runBlocking {
                withContext(IO) {
                    val data = authLocalDataSource.getAuthData()[0]

                    val form = FormBody.Builder()
                            .add("login", data.login)
                            .add("password", data.password).build()

                    val request = originResponse.request().newBuilder().post(form).build()
                    actualHeaders = chain.proceed(request).headers("Set-Cookie")
                }
            }
            val newRequest = originResponse.request().newBuilder()
                    .url(originUrl)
            actualHeaders.map { cookie -> newRequest.addHeader("Cookie", cookie) }
            return chain.proceed(newRequest.build())
        }
        return originResponse
    }
}