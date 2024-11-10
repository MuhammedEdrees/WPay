package org.codeslu.wpay.ui.notifications.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.codeslu.wpay.R
import org.codeslu.wpay.ui.theme.WPayTheme
import org.codeslu.wpay.ui.theme.backgroundLight
import org.codeslu.wpay.ui.theme.orange1Light
import org.codeslu.wpay.ui.theme.primaryContainerLight
import org.codeslu.wpay.ui.theme.surfaceDimLight
import org.codeslu.wpay.ui.theme.tertiaryContainerLight

@Composable
fun NotificationItem(
    modifier: Modifier = Modifier,
    isRead: Boolean = true,
    backgroundColor: Color,
    iconPainter: Painter,
    title: String,
    description: String = "",
    timestamp: String,
    hasLabel: Boolean,
    label: String = "",
    hasAction: Boolean,
    actionLabel: String = "",
    onActionClick: () -> Unit = {}
) {
    val readContainerColor = backgroundLight
    val unreadContainerColor = surfaceDimLight.copy(alpha = 0.4f)
    Surface(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(if (isRead) readContainerColor else unreadContainerColor)
            .padding(if (isRead) 0.dp else 16.dp),
        shape = RoundedCornerShape(16.dp),
        color = Color.Transparent
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box {
                Icon(
                    painter = iconPainter,
                    contentDescription = "$title notification",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .size(48.dp)
                        .background(backgroundColor, RoundedCornerShape(16.dp))
                        .padding(12.dp)
                )
                if (!isRead) {
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .background(color = unreadContainerColor, shape = CircleShape)
                            .padding(2.dp)
                            .background(color = orange1Light, shape = CircleShape)
                            .align(Alignment.TopEnd) // Position the badge at the top end of the icon
                    )
                }

            }
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    lineHeight = 22.sp,
                    maxLines = 1
                )
                Row(
                    modifier = Modifier.widthIn(max = 200.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (description.isNotBlank()) {
                        Text(
                            text = "$description • ",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                            fontSize = 12.sp,
                            lineHeight = 16.sp,
                            maxLines = 1
                        )
                    }
                    Text(
                        text = timestamp,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                        fontSize = 12.sp,
                        lineHeight = 16.sp,
                        maxLines = 1
                    )
                }
                if (hasAction) {
                    Button(
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent, contentColor = primaryContainerLight),
                        modifier = Modifier.widthIn(min = 1.dp).height(17.dp),
                        onClick = { onActionClick() },
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Text(
                            text = "$actionLabel >",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            lineHeight = 16.sp,
                            maxLines = 1
                        )
                    }
                }
            }
            if (hasLabel) {
                LabelItem(label = label)
            }
        }
    }
}

@Composable
private fun LabelItem(label: String) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        color = primaryContainerLight.copy(alpha = 0.08f),
        contentColor = primaryContainerLight
    ) {
        Box(
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.bodySmall,
                fontSize = 12.sp,
                lineHeight = 16.sp,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun NotificationItemPreview() {
    WPayTheme {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            NotificationItem(
                isRead = false,
                backgroundColor = primaryContainerLight,
                iconPainter = painterResource(id = R.drawable.ic_topup),
                title = "Top-up successful",
                description = "",
                timestamp = "8:00 AM",
                hasLabel = true,
                label = "Top-up",
                hasAction = false,
                actionLabel = ""
            ) {

            }
            Spacer(Modifier.size(16.dp))
            NotificationItem(
                isRead = true,
                backgroundColor = tertiaryContainerLight,
                iconPainter = painterResource(id = R.drawable.ic_history),
                title = "Black Friday Deal",
                description = "Get best discounts",
                timestamp = "7:30 AM",
                hasLabel = false,
                label = "",
                hasAction = true,
                actionLabel = "Browse Offers"
            ) {

            }
        }
    }
}