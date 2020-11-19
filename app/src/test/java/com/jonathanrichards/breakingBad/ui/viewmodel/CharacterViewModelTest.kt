package com.jonathanrichards.breakingBad.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jonathanrichards.breakingBad.data.network.contract.NetworkHelperContract
import com.jonathanrichards.breakingBad.utils.NetworkTestHelper
import com.jonathanrichards.breakingBad.utils.TestModule
import com.jonathanrichards.breakingBad.utils.TestResponse
import com.jraska.livedata.test
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject

class CharacterViewModelTest : KoinTest {

    val characterViewModel: CharacterViewModel by inject()

    val networkHelper: NetworkHelperContract by inject()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        startKoin {
            modules(listOf(TestModule.getTestModules()))
        }
        (networkHelper as NetworkTestHelper).setMockedResponse(
            TestResponse(
                "characterListResponse.json",
                200
            )
        )
        characterViewModel.fetchCharacterListResponse()

        characterViewModel.fullCharacterList.test()
            .awaitValue()
            .assertHasValue()
            .assertValue { it.isNotEmpty() }
    }

    @Test
    fun `test that we fetched the character list and it stores it in the livedata `() {

        characterViewModel.getCharacterList()
            .test()
            .awaitValue()
            .assertHasValue()
            .assertValue {
                it.isNotEmpty()
            }
    }

    @Test
    fun `test search Saul character`() {
        val saulName = "Saul"
        characterViewModel.searchCharacter(saulName)

        characterViewModel.getCharacterList().test()
            .awaitValue()
            .assertValue {
                it[0].name.contains(saulName)
            }
            .assertValue { it.size == 1 }
    }

    @Test
    fun `test filtering season appearance in season 5 and Gustavo Fring not being in it`() {
        val gustavoFringName = "Gustavo Fring"

        characterViewModel.filterSeasonAppearance(5)

        val characterList = characterViewModel.getCharacterList().test()
            .awaitValue()
            .value()

        for (character in characterList) {
            assert(gustavoFringName !in character.name)
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }
}