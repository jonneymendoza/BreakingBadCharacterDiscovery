package com.jonathanrichards.breakingBad.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jonathanrichards.breakingBad.data.Resource
import com.jonathanrichards.breakingBad.data.entity.BreakingBadCharacter
import com.jonathanrichards.breakingBad.data.network.CallHandler
import com.jonathanrichards.breakingBad.domain.usecase.GetCharactersUseCase

class CharacterViewModel constructor(private val useCase: GetCharactersUseCase) : ViewModel() {

    fun getCharacterList(): MutableLiveData<Resource<List<BreakingBadCharacter>>> {
        return CallHandler<List<BreakingBadCharacter>>().makeCall(useCase.getCharacterList())
    }
}