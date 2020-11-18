package com.jonathanrichards.breakingBad.utils

import com.jonathanrichards.breakingBad.data.network.contract.NetworkHelperContract
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import java.nio.charset.Charset

class NetworkTestHelper : NetworkHelperContract {

    private val mContentType = "application/json"

    private lateinit var response: TestResponse

    override fun createHttpClient(): OkHttpClient {
        val okHttpClient: OkHttpClient

        val builder = OkHttpClient.Builder()

        builder.addInterceptor { chain ->
            val originalRequest = chain.request()

            val requestBuilder = originalRequest.newBuilder()

            val newRequest = requestBuilder.build()
            val mockesResponse = Response.Builder()
                .code(response.responseCode)
                .message(getJsonString())
                .request(newRequest)
                .protocol(Protocol.HTTP_2)
                .body(ResponseBody.create(MediaType.parse(mContentType), getJsonString().toByteArray()))
                .addHeader("content-type", mContentType)
                .build()

            mockesResponse
        }

        builder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))


        okHttpClient = builder.build()
        return okHttpClient
    }

    fun setMockedResponse(response: TestResponse) {
        this.response = response
    }

    private fun getJsonString(): String {
        val inputStream = this.javaClass.classLoader?.getResourceAsStream(response.fileNameResponse)
        inputStream?.apply {
            return readBytes().toString(Charset.forName("utf-8"))
        }
        return ""
    }

}