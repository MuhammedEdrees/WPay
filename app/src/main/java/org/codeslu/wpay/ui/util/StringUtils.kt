package org.codeslu.wpay.ui.util

import java.text.NumberFormat
import java.util.Locale

object StringUtils {
    fun Double.formatMoney(): String {
        val formatter = NumberFormat.getCurrencyInstance(Locale.US)
        return formatter.format(this).replace(".00", "")
    }
}