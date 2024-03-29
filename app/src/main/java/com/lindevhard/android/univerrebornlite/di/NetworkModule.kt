package com.lindevhard.android.univerrebornlite.di

import android.annotation.SuppressLint
import android.content.SharedPreferences
import com.google.gson.GsonBuilder
import com.lindevhard.android.univerrebornlite.api.*
import com.lindevhard.android.univerrebornlite.data.DataSource.AuthLocalDataSource
import com.lindevhard.android.univerrebornlite.repository.AuthRepository
import com.lindevhard.android.univerrebornlite.utils.*
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager
import javax.security.cert.CertificateException

@Module(includes = [AppModule::class, DataBaseModule::class])
class NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(prefer: SharedPreferences, dataSource: AuthLocalDataSource): OkHttpClient {
        // TODO: Rewrite for use only *.kstu.kz certificate

        val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
            @SuppressLint("TrustAllX509TrustManager")
            @Throws(CertificateException::class)
            override fun checkClientTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {
            }

            @SuppressLint("TrustAllX509TrustManager")
            @Throws(CertificateException::class)
            override fun checkServerTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {
            }

            override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> {
                return arrayOf()
            }
        })

        // Install the all-trusting trust manager
        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCerts, java.security.SecureRandom())

        // Create an ssl socket factory with our all-trusting manager
        val sslSocketFactory = sslContext.socketFactory

        return OkHttpClient.Builder()
                .connectTimeout(10L, TimeUnit.SECONDS)
                .writeTimeout(10L, TimeUnit.SECONDS)
                .readTimeout(30L, TimeUnit.SECONDS)
                .cache(null)
                .addInterceptor(AuthorizationInterceptor(dataSource))
                .addInterceptor(AddCookiesInterceptor(prefer))
                .addInterceptor(ReceivedCookiesInterceptor(prefer))
                .sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
                .hostnameVerifier(HostnameVerifier { hostname, session -> true })
                .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val customDeserializers =
                GsonBuilder()
                        .registerTypeAdapter(ProfileData::class.java, ProfileDataDeserializer())
                        .registerTypeAdapter(ExamSchedule::class.java, ExamScheduleDeserializer())
                        .registerTypeAdapter(NewsList::class.java, NewsDeserializer())
                        .registerTypeAdapter(AttendanceList::class.java, AttendanceDeserializer())
                        .registerTypeAdapter(UkmdList::class.java, UmkdDataDeserializer())
                        .registerTypeAdapter(UmkdFilesList::class.java, UmkdFilesDeserializer())
                        .create()

        return Retrofit.Builder()
                .baseUrl(API_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(customDeserializers))
                .build()
    }

    @Singleton
    @Provides
    fun provideAuthRepository(retrofit: Retrofit, localDataSource: AuthLocalDataSource): AuthRepository {
        return AuthRepository(retrofit, localDataSource)
    }

}


