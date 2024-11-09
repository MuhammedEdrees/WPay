package org.codeslu.wpay.ui.util

import java.text.NumberFormat
import java.util.Locale
import kotlin.math.abs

object StringUtils {
    fun Double.formatMoney(isExpense: Boolean = false, padding: Int = 0): String {
        val formatter = NumberFormat.getCurrencyInstance(Locale.US).apply {
            minimumFractionDigits = padding
            maximumFractionDigits = padding
        }
        val amount = abs(this)
        val formattedAmount = formatter.format(amount)
        return (if (isExpense) "-" else "") + formattedAmount
    }
}