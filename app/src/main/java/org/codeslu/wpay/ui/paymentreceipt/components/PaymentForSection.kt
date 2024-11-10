package org.codeslu.wpay.ui.paymentreceipt.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.codeslu.wpay.R
import org.codeslu.wpay.data.model.Expense
import org.codeslu.wpay.ui.theme.notificationHeaderColor
import org.codeslu.wpay.ui.theme.onBackgroundLight
import org.codeslu.wpay.ui.theme.segmentedBarBackground
import org.codeslu.wpay.ui.util.LocalDateTimeUtil.formatLocalDateTime

@Composable
fun PaymentForSection(
    modifier: Modifier = Modifier,
    expense: Expense,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(R.string.payment_for),
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 16.sp,
            lineHeight = 22.sp,
            color = notificationHeaderColor
        )
        ListItem(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp)),
            colors = ListItemDefaults.colors(
                supportingColor = notificationHeaderColor,
                headlineColor = onBackgroundLight,
                containerColor = segmentedBarBackground
            ),
            leadingContent = {
                Icon(
                    painter = painterResource(id = expense.iconRes),
                    contentDescription = "${expense.title} icon",
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .size(48.dp)
                        .background(
                            color = expense.backgroundColor,
                            shape = RoundedCornerShape(16.dp)
                        )
                        .padding(12.dp)
                )
            },
            supportingContent = {
                Text(
                    text = expense.date.formatLocalDateTime(),
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = 12.sp,
                    lineHeight = 16.sp,
                )
            },
            headlineContent = {
                Text(
                    text = expense.title,
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 25.sp
                )
            })
    }
}