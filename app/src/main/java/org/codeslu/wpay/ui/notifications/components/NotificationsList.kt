package org.codeslu.wpay.ui.notifications.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.codeslu.wpay.R
import org.codeslu.wpay.data.model.GroupedNotifications
import org.codeslu.wpay.data.model.Notification
import org.codeslu.wpay.ui.theme.WPayTheme
import org.codeslu.wpay.ui.theme.notificationHeaderColor
import org.codeslu.wpay.ui.theme.primaryContainerLight

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NotificationsList(
    modifier: Modifier = Modifier,
    groupedNotifications: List<GroupedNotifications>,
    hasUnread: Boolean = false,
    onMarkAllAsRead: () -> Unit,
    onActionClicked: (String) -> Unit,
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        groupedNotifications.forEachIndexed { index, groupedItem ->
            stickyHeader {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = groupedItem.title,
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 14.sp,
                        lineHeight = 19.sp,
                        color = notificationHeaderColor
                    )
                    if (index == 0) {
                        TextButton(
                            modifier = Modifier.height(20.dp),
                            enabled = hasUnread,
                            onClick = { onMarkAllAsRead() },
                            contentPadding = PaddingValues(0.dp)
                        ) {
                            Text(
                                stringResource(R.string.mark_as_read),
                                style = MaterialTheme.typography.bodyMedium,
                                fontSize = 14.sp,
                                lineHeight = 19.sp,
                                color = primaryContainerLight
                            )
                        }
                    }
                }

            }
            items(groupedItem.notifications) { notification ->
                NotificationItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    isRead = notification.isRead,
                    backgroundColor = getColorForNotification(notification.type),
                    iconPainter = getPainterForNotification(notification.type),
                    title = notification.title,
                    description = notification.description,
                    timestamp = getTimeStampForNotification(notification.createdAtEpochSeconds),
                    hasLabel = notification.hasLabel,
                    label = notification.label,
                    hasAction = notification.hasAction,
                    actionLabel = notification.actionLabel,
                    onActionClick = { onActionClicked(notification.actionId) }
                )
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun NotificationsListPreview() {
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
    WPayTheme {
        NotificationsList(groupedNotifications = groups, onMarkAllAsRead = { /*TODO*/ }) {

        }
    }
}