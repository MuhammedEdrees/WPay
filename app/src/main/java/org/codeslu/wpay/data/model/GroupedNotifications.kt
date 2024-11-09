package org.codeslu.wpay.data.model

data class GroupedNotifications(
    val title: String = "",
    val notifications: List<Notification> = emptyList()
)
