package com.jonathanrichards.breakingBad.domain.repository

import com.jonathanrichards.breakingBad.data.entity.BreakingBadCharacter
import kotlinx.coroutines.Deferred
import retrofit2.Response

interface BreakingBadRepositryContract {
    fun getCharacterList(): Deferred<Response<List<BreakingBadCharacter>>>
}