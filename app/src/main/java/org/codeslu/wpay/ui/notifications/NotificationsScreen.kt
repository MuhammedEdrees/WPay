package org.codeslu.wpay.ui.notifications

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.codeslu.wpay.data.model.GroupedNotifications
import org.codeslu.wpay.data.model.Notification
import org.codeslu.wpay.ui.notifications.components.NotificationsList
import org.codeslu.wpay.ui.notifications.components.NotificationsTopAppBar
import org.codeslu.wpay.ui.theme.WPayTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun NotificationsScreen(
    modifier: Modifier = Modifier,
    viewModel: NotificationsViewModel = koinViewModel(),
    onBackClicked: () -> Unit = {},
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    NotificationsScreenContent(
        groupedNotifications = state.groupedNotifications,
        hasUnread = state.hasUnread,
        onAction = { action ->
            when (action) {
                is NotificationsUiAction.OnBackClicked -> onBackClicked()
                else -> Unit
            }
        }
    )
}

@Composable
private fun NotificationsScreenContent(
    modifier: Modifier = Modifier,
    groupedNotifications: List<GroupedNotifications> = emptyList(),
    hasUnread: Boolean = false,
    onAction: (NotificationsUiAction) -> Unit = {},
) {
    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        containerColor = Color.Transparent,
        topBar = {
            NotificationsTopAppBar(
                onBackClicked = { onAction(NotificationsUiAction.OnBackClicked) },
                onSettingsClicked = { onAction(NotificationsUiAction.OnSettingsClicked) },
            )
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
            color = MaterialTheme.colorScheme.surface,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(width = 48.dp, height = 5.dp)
                        .background(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f))
                        .clip(
                            RoundedCornerShape(8.dp)
                        )
                )
                NotificationsList(
                    groupedNotifications = groupedNotifications,
                    hasUnread = hasUnread,
                    onMarkAllAsRead = { onAction(NotificationsUiAction.OnMarkAsReadClicked) },
                    onActionClicked = {
                        onAction(
                            NotificationsUiAction.OnNotificationActionClicked(
                                it
                            )
                        )
                    }
                )
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun NotificationsScreenContentPreview() {
    WPayTheme {
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
        NotificationsScreenContent(
            groupedNotifications = groups,
            hasUnread = true
        )
    }
}