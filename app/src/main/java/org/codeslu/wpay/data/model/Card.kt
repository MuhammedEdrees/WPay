package org.codeslu.wpay.data.model

import androidx.annotation.DrawableRes
import org.codeslu.wpay.R

data class Card(
    val title: String = "",
    @DrawableRes
    val iconRes: Int = R.drawable.ic_logo,
    val number: String = "",
)
