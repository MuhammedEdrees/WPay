package org.codeslu.wpay.data.model

import kotlinx.datetime.Clock


data class Notification(
    val id: String = "",
    val title: String,
    val description: String,
    val type: String = "",
    val hasLabel: Boolean = false,
    val label: String = "",
    val createdAtEpochSeconds: Long = Clock.System.now().epochSeconds,
    val isRead: Boolean = true,
    val hasAction: Boolean = false,
    val actionLabel: String = "",
    val actionId: String = ""
)
