package com.jonathanrichards.breakingBad.ui.components.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

object AppTheme {

    private val lightbrown = Color(red = 253, green = 218, blue = 159)
    private val lightBrownAlternative = Color(red = 240, green = 177, blue = 106)
    private val brown = Color(red = 124, green = 90, blue = 64)
    private val darkBrown = Color(red = 50, green = 20, blue = 1)
    private val secondaryBrown = Color(167, 113, 89)

    val defaultTheme = lightColors(
        primary = lightbrown,
        secondary = secondaryBrown,
        background = lightbrown,
        surface = darkBrown,
        onPrimary = darkBrown,
        primaryVariant = lightBrownAlternative,
        onSecondary = brown,
        onBackground = brown,
        onSurface = lightBrownAlternative
    )

    private val MyTypography = Typography(
        h1 = TextStyle(
            fontWeight = FontWeight.W300,
            fontSize = 96.sp
        ),
        body1 = TextStyle(
            fontSize = 16.sp,
        )
    )

    @Composable
    fun breakingBadTheme(content: @Composable () -> Unit) {
        MaterialTheme(
            colors = defaultTheme, typography = MyTypography
        ) {
            content()
        }
    }
}