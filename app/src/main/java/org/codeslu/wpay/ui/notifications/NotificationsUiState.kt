package org.codeslu.wpay.ui.notifications

import org.codeslu.wpay.data.model.GroupedNotifications


data class NotificationsUiState(
    val groupedNotifications: List<GroupedNotifications> = emptyList(),
    val hasUnread: Boolean = false,
    val unreadNotificationsIds: List<String> = emptyList()
)
