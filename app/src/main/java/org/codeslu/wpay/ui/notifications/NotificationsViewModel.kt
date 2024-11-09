package org.codeslu.wpay.ui.notifications

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.codeslu.wpay.data.model.GroupedNotifications
import org.codeslu.wpay.data.model.Notification

class NotificationsViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(NotificationsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        val groups = listOf(
            GroupedNotifications(
                title = "Today",
                notifications = listOf(
                    Notification(
                        title = "New Message",
                        description = "You have received a new message from Alice.",
                        type = "Message",
                        hasLabel = true,
                        label = "Important",
                        createdAtEpochSeconds = 0L,
                        isRead = false,
                        hasAction = true,
                        actionLabel = "Reply",
                        actionId = "reply_message_1"
                    )

                ),
            ),
            GroupedNotifications(
                title = "Yesterday",
                notifications = listOf(
                    Notification(
                        title = "Order Shipped",
                        description = "Your order #12345 has been shipped and is on its way.",
                        type = "Order",
                        hasLabel = false,
                        createdAtEpochSeconds = 0L, // 3 days ago
                        isRead = true,
                        hasAction = true,
                        actionLabel = "Track Order",
                        actionId = "track_order_12345"
                    ),
                    Notification(
                        title = "Weekly Summary",
                        description = "Here is your weekly summary of activities and updates.",
                        type = "Summary",
                        hasLabel = false,
                        createdAtEpochSeconds = 0L, // 1 week ago
                        isRead = true,
                        hasAction = false
                    )

                ),
            ),
        )
        viewModelScope.launch {
            _uiState.update { it.copy(groupedNotifications = groups, hasUnread = true) }
        }
    }
}