package com.jonathanrichards.breakingBad.ui

import com.jonathanrichards.breakingBad.domain.usecase.GetCharactersUseCase
import com.jonathanrichards.breakingBad.ui.viewmodel.CharacterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object UIModule {
    val getUiModule = module {
        factory { GetCharactersUseCase(get()) }
        viewModel { CharacterViewModel(get()) }
    }
}