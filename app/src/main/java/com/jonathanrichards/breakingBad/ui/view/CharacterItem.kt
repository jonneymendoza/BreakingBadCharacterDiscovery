package com.jonathanrichards.breakingBad.ui.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.defaultMinSizeConstraints
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jonathanrichards.breakingBad.data.entity.BreakingBadCharacter
import com.jonathanrichards.breakingBad.ui.components.theme.AppTheme
import dev.chrisbanes.accompanist.coil.CoilImage

class CharacterItem constructor(
    private val breakingBadCharacter: BreakingBadCharacter,
    private val onClick: () -> Unit
) {

    @Composable
    fun showCharacterItemView() {
            Surface(
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(8.dp), elevation = 2.dp
            ) {
                Column(Modifier.clickable(onClick = onClick)) {
                    CoilImage(
                        data = breakingBadCharacter.characterImage,
                        fadeIn = true,
                        modifier = Modifier.padding(5.dp).defaultMinSizeConstraints(100.dp)
                    )
                    Text(
                        text = breakingBadCharacter.name,
                        Modifier.absolutePadding(4.dp, 8.dp, 4.dp, 8.dp)
                    )
                }
            }
    }
}