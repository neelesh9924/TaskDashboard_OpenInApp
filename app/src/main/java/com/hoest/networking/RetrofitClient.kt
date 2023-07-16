package com.hoest.networking

import com.hoest.networking.RetrofitInterface
import okhttp3.Interceptor
import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.CookieManager
import java.util.TimeZone
import java.util.concurrent.TimeUnit

class RetrofitClient {

    fun getClient(): Retrofit {

        val interceptor = HttpLoggingInterceptor()

        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val cookieHandler = CookieManager()

        val client: OkHttpClient = OkHttpClient.Builder()
            .addNetworkInterceptor(interceptor)
            .addInterceptor(Interceptor.invoke { chain ->
                return@invoke chain.proceed(
                    chain.request().newBuilder()
                        .addHeader("timezone", TimeZone.getDefault().id).build()
                )
            })
            .cookieJar(JavaNetCookieJar(cookieHandler))
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl(RetrofitInterface.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

    }

}