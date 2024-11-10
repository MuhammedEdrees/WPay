package org.codeslu.wpay.ui.statistics.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.codeslu.wpay.ui.theme.WPayTheme
import org.codeslu.wpay.ui.theme.notificationHeaderColor
import org.codeslu.wpay.ui.theme.orange1Light
import org.codeslu.wpay.ui.theme.outlineLight
import org.codeslu.wpay.ui.theme.primaryContainerLight
import org.codeslu.wpay.ui.theme.surfaceVariantLight

@Composable
fun BarItem(
    modifier: Modifier = Modifier,
    chartEntryTitle: String,
    income: Double,
    expenses: Double
) {
    val weight by remember(income, expenses) {
        mutableFloatStateOf((expenses / (income + expenses)).toFloat())
    }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Column(
            modifier = Modifier.size(height = 160.dp, width = 10.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .width(10.dp)
                    .weight(weight)
                    .background(shape = RoundedCornerShape(10.dp), color = orange1Light)

            )
            Box(
                modifier = Modifier
                    .width(10.dp)
                    .weight(1-weight)
                    .background(color = primaryContainerLight, shape = RoundedCornerShape(10.dp))
            )
        }
        Text(
            text = chartEntryTitle,
            style = MaterialTheme.typography.bodySmall,
            color = notificationHeaderColor,
            fontSize = 14.sp,
            lineHeight = 19.sp
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun BarItemPreview() {
    WPayTheme {
        BarItem(
            chartEntryTitle = "Wed",
            income = 100.0,
            expenses = 50.0
        )
    }
}