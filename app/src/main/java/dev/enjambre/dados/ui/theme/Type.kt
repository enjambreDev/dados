package dev.enjambre.dados.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import dev.enjambre.dados.R

val FrederikaTheGreat = FontFamily(Font(R.font.frederika_the_great_regular))

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = FrederikaTheGreat,
        fontSize = 24.sp
    ),
    displayLarge = TextStyle(
        fontFamily = FrederikaTheGreat,
        fontSize = 80.sp
    )
)