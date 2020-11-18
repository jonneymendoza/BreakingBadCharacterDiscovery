package com.jonathanrichards.breakingBad.domain

import com.jonathanrichards.breakingBad.domain.repository.BreakingBadRepository
import com.jonathanrichards.breakingBad.domain.repository.BreakingBadRepositryContract
import org.koin.dsl.module

object DomainModule {
    val getCharacterRepositoryModule = module {
        factory<BreakingBadRepositryContract> { BreakingBadRepository(get()) }
    }
}