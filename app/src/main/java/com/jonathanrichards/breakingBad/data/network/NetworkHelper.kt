package com.jonathanrichards.breakingBad.data.network

import com.jonathanrichards.breakingBad.BuildConfig
import com.jonathanrichards.breakingBad.data.network.contract.NetworkHelperContract
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

class NetworkHelper : NetworkHelperContract {
    override fun createHttpClient(): OkHttpClient {
        val okHttpClient: OkHttpClient

        val builder = OkHttpClient.Builder()

        builder.addInterceptor { chain ->
            val originalRequest = chain.request()

            val requestBuilder = originalRequest.newBuilder()

            val newRequest = requestBuilder.build()
            val response = chain.proceed(newRequest)
            response
        }

        if (BuildConfig.DEBUG) {
            builder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        }
        okHttpClient = builder.build()
        return okHttpClient
    }

}