package org.codeslu.wpay.ui.statistics.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.codeslu.wpay.R
import org.codeslu.wpay.data.model.Expense
import org.codeslu.wpay.ui.theme.WPayTheme
import org.codeslu.wpay.ui.theme.onPrimaryContainerLight
import org.codeslu.wpay.ui.theme.outlineLight
import org.codeslu.wpay.ui.util.LocalDateTimeUtil.formatLocalDateTime
import org.codeslu.wpay.ui.util.StringUtils.formatMoney

@Composable
fun RecentExpenseItem(
    modifier: Modifier = Modifier,
    recentExpense: Expense,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Icon(
            painter = painterResource(id = recentExpense.iconRes),
            contentDescription = "${recentExpense.title} icon",
            tint = Color.Unspecified,
            modifier = Modifier
                .size(48.dp)
                .background(
                    color = recentExpense.backgroundColor,
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(12.dp)
        )
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(
                text = recentExpense.title,
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 22.sp
            )
            Text(
                text = recentExpense.date.formatLocalDateTime(),
                style = MaterialTheme.typography.bodySmall,
                fontSize = 12.sp,
                lineHeight = 16.sp,
                color = outlineLight.copy(alpha = 0.7f)
            )

        }
        Text(
            text = recentExpense.value.formatMoney(isExpense = true, padding = 2),
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 22.sp
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun RecentExpenseItemPreview() {
    WPayTheme {
        val item = Expense(
            title = "Starbucks Coffee",
            value = 156.0,
            iconRes = R.drawable.ic_starbucks,
            backgroundColor = onPrimaryContainerLight,
        )
        RecentExpenseItem(modifier = Modifier.padding(16.dp), recentExpense = item)
    }
}