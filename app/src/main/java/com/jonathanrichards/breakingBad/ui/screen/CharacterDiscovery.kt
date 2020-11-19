package com.jonathanrichards.breakingBad.ui.screen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.jonathanrichards.breakingBad.R
import com.jonathanrichards.breakingBad.data.Status
import com.jonathanrichards.breakingBad.data.entity.BreakingBadCharacter
import com.jonathanrichards.breakingBad.ui.components.GridItems
import com.jonathanrichards.breakingBad.ui.components.theme.AppTheme.breakingBadTheme
import com.jonathanrichards.breakingBad.ui.view.CharacterItem
import com.jonathanrichards.breakingBad.ui.viewmodel.CharacterViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterDiscovery : AppCompatActivity() {

    private val viewModel: CharacterViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            setupView()
        }
        getCharactersList()
    }

    private fun getCharactersList() {
        viewModel.fetchCharacterListResponse()
    }

    @Composable
    fun setupView() {
        val responseStatus by viewModel.getResponseStatus().observeAsState()

        when (responseStatus) {
            Status.Error -> breakingBadTheme {
                Text(text = getString(R.string.errorText), style = MaterialTheme.typography.caption)
            }
            Status.Loading -> breakingBadTheme {
                Text(text = getString(R.string.loadingText), style = MaterialTheme.typography.h1)
            }
            Status.Success -> {
                showCharacterListView()
            }
        }
    }

    @Composable
    fun showCharacterListView() {
        val characterList: List<BreakingBadCharacter> by viewModel.getCharacterList()
            .observeAsState(listOf())

        val textState = remember { mutableStateOf(TextFieldValue()) }
        breakingBadTheme {
            Column {
                Text(text = getString(R.string.enterNameField))
                Row() {
                    TextField(
                        value = textState.value,
                        onValueChange = {
                            viewModel.searchCharacter(it.text)
                            textState.value = it
                        }
                    )
                }
                SeasonChooser()
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
    }

    @Composable
    fun SeasonChooser() {
        val radioOptions = listOf(1, 2, 3, 4, 5, 0)
        val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[5]) }

        Column {
            radioOptions.forEach { season ->
                Row(Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = (season == selectedOption),
                        onClick = {
                            (season == selectedOption)
                            onOptionSelected(season)
                            viewModel.filterSeasonAppearance(season)
                        }
                    )
                    .padding(horizontal = 16.dp)
                ) {
                    RadioButton(
                        selected = (season == selectedOption),
                        onClick = {
                            (season == selectedOption)
                            onOptionSelected(season)
                            viewModel.filterSeasonAppearance(season)
                        }
                    )
                    Text(
                        text = if (season == 0) "All seasons" else "Season $season",
                        style = MaterialTheme.typography.body1.merge(),
                        modifier = Modifier.padding(start = 16.dp)
                    )
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

