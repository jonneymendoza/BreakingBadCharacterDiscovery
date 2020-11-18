package com.jonathanrichards.breakingBad.ui.screen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.jonathanrichards.breakingBad.R
import com.jonathanrichards.breakingBad.data.Resource
import com.jonathanrichards.breakingBad.data.Status
import com.jonathanrichards.breakingBad.data.entity.BreakingBadCharacter
import com.jonathanrichards.breakingBad.ui.components.GridItems
import com.jonathanrichards.breakingBad.ui.components.theme.AppTheme.breakingBadTheme
import com.jonathanrichards.breakingBad.ui.view.CharacterItem
import com.jonathanrichards.breakingBad.ui.viewmodel.CharacterViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterDiscovery : AppCompatActivity() {

    private val viewModel: CharacterViewModel by viewModel()

    private val getCharactersLiveData = MutableLiveData<Resource<List<BreakingBadCharacter>>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            setupView()
        }
        getCharactersList()
    }

    private fun getCharactersList() {
        viewModel.getCharacterList().observe(this, Observer {
            getCharactersLiveData.postValue(it)
        })
    }

    @Composable
    fun setupView() {
        val characterList by getCharactersLiveData.observeAsState()

        when (characterList?.status) {
            Status.Error -> breakingBadTheme {
                Text(text = getString(R.string.errorText), style = MaterialTheme.typography.caption)
            }
            Status.Loading -> breakingBadTheme {
                Text(text = getString(R.string.errorText), style = MaterialTheme.typography.h1)
            }
            Status.Success -> createCharacterListView(characterList?.data.orEmpty())
        }
    }

    @Composable
    fun createCharacterListView(characterList: List<BreakingBadCharacter>) {
        breakingBadTheme {
            Box(
                modifier = Modifier.fillMaxSize().background(
                    MaterialTheme.colors.primary
                )
            ) {
                LazyColumn {
                    GridItems(
                        items = characterList,
                        columns = 2
                    ) { character ->
                        CharacterItem(character, goToDetails(character)).showCharacterItemView()
                    }
                }
            }
        }
    }

    private fun goToDetails(character: BreakingBadCharacter): () -> Unit {
        return {
            startActivity(CharacterDetails.createCharacterIntent(this, character))
        }
    }
}

