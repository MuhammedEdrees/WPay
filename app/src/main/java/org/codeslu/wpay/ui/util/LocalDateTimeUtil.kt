package org.codeslu.wpay.ui.util

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toInstant

object LocalDateTimeUtil {
    fun LocalDateTime.formatLocalDateTime(): String {
        val months = arrayOf(
            "Jan", "Feb", "Mar", "Apr", "May", "Jun",
            "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
        )
        val month = months[this.monthNumber - 1]
        val day = this.dayOfMonth
        val year = this.year
        val hour = this.hour % 12
        val adjustedHour = if (hour == 0) 12 else hour
        val minute = this.minute.toString().padStart(2, '0')
        val period = if (this.hour < 12) "AM" else "PM"
        return "$month $day, $year • $adjustedHour:$minute $period"
    }
}