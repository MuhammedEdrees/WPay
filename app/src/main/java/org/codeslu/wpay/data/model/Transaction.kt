package org.codeslu.wpay.data.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import kotlinx.datetime.LocalDateTime
import org.codeslu.wpay.ui.theme.surfaceLight
import org.codeslu.wpay.ui.util.LocalDateTimeUtil.now

data class Transaction(
    val title: String = "",
    @DrawableRes
    val iconRes: Int = 0,
    val iconBackgroundColor: Color = surfaceLight,
    val date: LocalDateTime = LocalDateTime.now(),
    val amount: Double = 0.0,
    val paymentFee: Double = 0.0,
){
    val totalAmount: Double
        get() = amount + paymentFee
}
