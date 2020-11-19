package com.jonathanrichards.breakingBad.utils

import com.jonathanrichards.breakingBad.data.network.NetworkController
import com.jonathanrichards.breakingBad.data.network.contract.NetworkControllerContract
import com.jonathanrichards.breakingBad.data.network.contract.NetworkHelperContract
import com.jonathanrichards.breakingBad.domain.repository.BreakingBadRepository
import com.jonathanrichards.breakingBad.domain.repository.BreakingBadRepositryContract
import com.jonathanrichards.breakingBad.domain.usecase.GetCharactersUseCase
import com.jonathanrichards.breakingBad.ui.viewmodel.CharacterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object TestModule {
    fun getTestModules() = module {
        factory<BreakingBadRepositryContract> { BreakingBadRepository(get()) }
        single<NetworkHelperContract> { NetworkTestHelper() }
        single<NetworkControllerContract> { NetworkController(get()) }
        factory { GetCharactersUseCase(get()) }
        viewModel { CharacterViewModel(get()) }
    }
}