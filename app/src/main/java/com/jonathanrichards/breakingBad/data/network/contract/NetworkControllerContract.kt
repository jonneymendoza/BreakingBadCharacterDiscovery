package com.jonathanrichards.breakingBad.data.network.contract

import com.jonathanrichards.breakingBad.data.entity.BreakingBadCharacter
import kotlinx.coroutines.Deferred
import retrofit2.Response

interface NetworkControllerContract {
    fun getCharacterList(): Deferred<Response<List<BreakingBadCharacter>>>
}