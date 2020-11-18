package com.jonathanrichards.breakingBad.main

import com.jonathanrichards.breakingBad.data.network.NetworkModule
import com.jonathanrichards.breakingBad.domain.DomainModule
import com.jonathanrichards.breakingBad.ui.UIModule

object AppComponent {
    val getAllModules =
        listOf(
            DomainModule.getCharacterRepositoryModule,
            NetworkModule.networkModule,
            UIModule.getUiModule
        )
}