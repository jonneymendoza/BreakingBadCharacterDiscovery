package com.jonathanrichards.breakingBad.data.network

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.jonathanrichards.breakingBad.BuildConfig
import com.jonathanrichards.breakingBad.data.entity.BreakingBadCharacter
import com.jonathanrichards.breakingBad.data.network.contract.BreakingBadApi
import com.jonathanrichards.breakingBad.data.network.contract.NetworkControllerContract
import com.jonathanrichards.breakingBad.data.network.contract.NetworkHelperContract
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkController constructor(private val networkHelper: NetworkHelperContract) : NetworkControllerContract {

    private fun getApi(): BreakingBadApi{
        val gson = GsonBuilder().setLenient().create()
        val httpClient = networkHelper.createHttpClient()
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

        return retrofit.create(BreakingBadApi::class.java)
    }

    override fun getCharacterList(): Deferred<Response<List<BreakingBadCharacter>>> {
        return getApi().getCharacterList()
    }
}

