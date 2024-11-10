package org.codeslu.wpay.data.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.codeslu.wpay.ui.theme.primaryContainerLight

data class Expense(
    val amount: Double = 0.0,
    val title: String = "",
    val date: LocalDateTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
    @DrawableRes
    val iconRes: Int = 0,
    val backgroundColor: Color = primaryContainerLight
)
