package com.jonathanrichards.breakingBad.data.network.contract

import okhttp3.OkHttpClient

interface NetworkHelperContract{
    fun createHttpClient() : OkHttpClient
}