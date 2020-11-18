package com.jonathanrichards.breakingBad.domain.usecase

import com.jonathanrichards.breakingBad.data.entity.BreakingBadCharacter
import com.jonathanrichards.breakingBad.domain.repository.BreakingBadRepositryContract
import kotlinx.coroutines.Deferred
import retrofit2.Response

class GetCharactersUseCase constructor(private val repository: BreakingBadRepositryContract) {

    fun getCharacterList(): Deferred<Response<List<BreakingBadCharacter>>> {
        return repository.getCharacterList()
    }
}