package org.codeslu.wpay.ui.notifications.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.codeslu.wpay.R
import org.codeslu.wpay.ui.theme.orange1Light
import org.codeslu.wpay.ui.theme.primaryContainerLight
import org.codeslu.wpay.ui.theme.secondaryContainerLight
import org.codeslu.wpay.ui.theme.tertiaryContainerLight

fun getTimeStampForNotification(createdAtEpochSeconds: Long): String {
    val instant = Instant.fromEpochSeconds(createdAtEpochSeconds)
    val dateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())
    var hour = dateTime.hour
    val minute = dateTime.minute.toString().padStart(2, '0')
    val amPm = if (hour >= 12) "PM" else "AM"
    if (hour == 0) {
        hour = 12
    } else if (hour > 12) {
        hour -= 12
    }
    val hourFormatted = hour.toString().padStart(2, '0')
    return "$hourFormatted:$minute $amPm"

}

fun getColorForNotification(type: String): Color {
    //todo: return certain colors based on type
    val colors = listOf(
        primaryContainerLight,
        secondaryContainerLight,
        tertiaryContainerLight,
        orange1Light,
    )
    return colors.random().copy(alpha = 0.5f)
}

@Composable
fun getPainterForNotification(type: String): Painter {
    //todo: return certain icons based on type
    val ids = listOf(
        R.drawable.ic_topup,
        R.drawable.ic_transfer,
        R.drawable.ic_history,
        R.drawable.ic_merchant,
    )
    return painterResource(id = ids.random())
}