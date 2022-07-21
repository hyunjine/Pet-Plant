package com.hyunjine.petplant.data.retrofit

import com.kakao.sdk.network.ApiFactory.loggingInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object FirebaseTokenClient {

    private var okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(60, TimeUnit.SECONDS)
        .build()
    fun getClient(): FirebaseTokenService {

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://192.168.0.5:8080")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FirebaseTokenService::class.java)
    }
}