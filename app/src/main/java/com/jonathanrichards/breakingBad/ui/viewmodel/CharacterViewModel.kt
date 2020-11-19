package com.jonathanrichards.breakingBad.ui.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.jonathanrichards.breakingBad.data.Resource
import com.jonathanrichards.breakingBad.data.Status
import com.jonathanrichards.breakingBad.data.entity.BreakingBadCharacter
import com.jonathanrichards.breakingBad.data.network.CallHandler
import com.jonathanrichards.breakingBad.domain.usecase.GetCharactersUseCase

class CharacterViewModel constructor(private val useCase: GetCharactersUseCase) : ViewModel() {

    private val characterList = MutableLiveData<List<BreakingBadCharacter>>()

    @VisibleForTesting
    var fullCharacterList = MutableLiveData<List<BreakingBadCharacter>>()

    private val responseStatus = MutableLiveData<Status>()

    private lateinit var getCharactersLiveData: MutableLiveData<Resource<List<BreakingBadCharacter>>>

    private val characterObserver = Observer<Resource<List<BreakingBadCharacter>>> {
        when (it.status) {
            Status.Error -> responseStatus.postValue(Status.Error)
            Status.Loading -> responseStatus.postValue(Status.Loading)
            Status.Success -> {
                responseStatus.postValue(Status.Success)
                fullCharacterList.postValue(it.data.orEmpty())
                characterList.postValue(it.data.orEmpty())
            }
        }
    }

    fun fetchCharacterListResponse() {
        getCharactersLiveData =
            CallHandler<List<BreakingBadCharacter>>().makeCall(useCase.getCharacterList())
        getCharactersLiveData.observeForever(characterObserver)

    }

    fun getCharacterList(): MutableLiveData<List<BreakingBadCharacter>> {
        return characterList
    }

    fun getResponseStatus(): MutableLiveData<Status> {
        return responseStatus
    }

    override fun onCleared() {
        getCharactersLiveData.removeObserver(characterObserver)
        super.onCleared()
    }

    fun filterSeasonAppearance(season: Int) {

        //as in, selected "all seasons"
        if (season == 0) {
            characterList.postValue(fullCharacterList.value)
        }
        characterList.postValue(fullCharacterList.value?.let { characterList ->
            characterList.filter {
                if (it.seasonAppearance.isNullOrEmpty()) {
                    false
                } else {
                    it.seasonAppearance.contains(season)
                }
            }
        })
    }

    fun searchCharacter(name: String) {
        characterList.postValue(fullCharacterList.value?.let { characterList ->
            characterList.filter {
                it.name.contains(name)
            }
        })
    }
}