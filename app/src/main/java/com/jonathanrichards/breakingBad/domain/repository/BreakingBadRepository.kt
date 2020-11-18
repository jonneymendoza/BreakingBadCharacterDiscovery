package com.jonathanrichards.breakingBad.domain.repository

import com.jonathanrichards.breakingBad.data.entity.BreakingBadCharacter
import com.jonathanrichards.breakingBad.data.network.contract.NetworkControllerContract
import kotlinx.coroutines.Deferred
import retrofit2.Response

class BreakingBadRepository  constructor(private val networkController: NetworkControllerContract) : BreakingBadRepositryContract{
    override fun getCharacterList(): Deferred<Response<List<BreakingBadCharacter>>> {
        return networkController.getCharacterList()
    }
}