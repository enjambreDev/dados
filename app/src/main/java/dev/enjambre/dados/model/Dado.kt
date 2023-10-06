package dev.enjambre.dados.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Dado(
    @DrawableRes val drawableRes: Int,
    @StringRes val contentDescription: Int,
    val lados: Int,
    val currentValue: Int = lados
)