package com.jonathanrichards.breakingBad.data.network

import com.jonathanrichards.breakingBad.data.network.contract.NetworkControllerContract
import com.jonathanrichards.breakingBad.data.network.contract.NetworkHelperContract
import org.koin.dsl.module

object NetworkModule {
    val networkModule = module {
        single<NetworkHelperContract> { NetworkHelper() }
        single<NetworkControllerContract> { NetworkController(get()) }
    }
}