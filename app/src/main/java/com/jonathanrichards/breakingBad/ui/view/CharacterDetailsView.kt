package com.jonathanrichards.breakingBad.ui.view

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jonathanrichards.breakingBad.data.entity.BreakingBadCharacter
import com.jonathanrichards.breakingBad.ui.components.theme.AppTheme.breakingBadTheme
import dev.chrisbanes.accompanist.coil.CoilImage

/**
 * Character details screen UI
 */
class CharacterDetailsView constructor(private val breakingBadCharacter: BreakingBadCharacter) {

    @Composable
    fun showCharacterDetails() {
        breakingBadTheme() {
            Box(modifier = Modifier.fillMaxSize().padding(8.dp)) {
                ScrollableColumn() {
                    CoilImage(
                        data = breakingBadCharacter.characterImage,
                        fadeIn = true,
                        modifier = Modifier.padding(5.dp)
                    )
                    Row() {
                        Text(
                            text = "Name: ",
                            style = TextStyle(fontWeight = FontWeight.Black),
                            modifier = Modifier.absolutePadding(4.dp, 8.dp, 4.dp, 8.dp)
                        )
                        Text(
                            text = breakingBadCharacter.name,
                            Modifier.absolutePadding(4.dp, 8.dp, 4.dp, 8.dp)
                        )
                    }

                    Row() {
                        Text(
                            text = "Occupation: ",
                            style = TextStyle(fontWeight = FontWeight.Black),
                            modifier = Modifier.absolutePadding(4.dp, 8.dp, 4.dp, 8.dp)
                        )
                        Text(
                            text = breakingBadCharacter.occupation.joinToString(separator = ", "),
                            modifier = Modifier.absolutePadding(4.dp, 8.dp, 4.dp, 8.dp)
                        )
                    }

                    breakingBadCharacter.status?.let {
                        Row() {
                            Text(
                                text = "Status: ",
                                style = TextStyle(fontWeight = FontWeight.Black),
                                modifier = Modifier.absolutePadding(4.dp, 8.dp, 4.dp, 8.dp)
                            )
                            Text(
                                text = it,
                                modifier = Modifier.absolutePadding(4.dp, 8.dp, 4.dp, 8.dp)
                            )
                        }
                    }

                    breakingBadCharacter.nickname?.let {
                        Row() {
                            Text(
                                text = "Nickname: ",
                                style = TextStyle(fontWeight = FontWeight.Black),
                                modifier = Modifier.absolutePadding(4.dp, 8.dp, 4.dp, 8.dp)
                            )
                            Text(
                                text = it,
                                modifier = Modifier.absolutePadding(4.dp, 8.dp, 4.dp, 8.dp)
                            )
                        }
                    }

                    breakingBadCharacter.seasonAppearance?.let {
                        Row() {
                            Text(
                                text = "Season Appearances: ",
                                style = TextStyle(fontWeight = FontWeight.Black),
                                modifier = Modifier.absolutePadding(4.dp, 8.dp, 4.dp, 8.dp)
                            )
                            Text(
                                text = breakingBadCharacter.seasonAppearance.joinToString(separator = ", "),
                                modifier = Modifier.absolutePadding(4.dp, 8.dp, 4.dp, 8.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}