package org.codeslu.wpay.ui.statistics.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.codeslu.wpay.R
import org.codeslu.wpay.data.model.PieChartData
import org.codeslu.wpay.data.model.PieChartEntry
import org.codeslu.wpay.ui.theme.WPayTheme
import org.codeslu.wpay.ui.theme.orange1Light
import org.codeslu.wpay.ui.theme.outlineLight
import org.codeslu.wpay.ui.theme.primaryContainerLight
import org.codeslu.wpay.ui.theme.secondaryContainerLight
import org.codeslu.wpay.ui.util.DummyValues.pieChartData
import org.codeslu.wpay.ui.util.StringUtils.formatMoney

@Composable
fun CategoryPieChartSection(
    modifier: Modifier = Modifier,
    lastWeekExpenses: Double,
    chartData: PieChartData,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CategoryChartHeader(lastWeekExpenses)
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            CircularPieChart(
                charts = chartData.entries.take(3),
                totalValue = lastWeekExpenses
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_coffee),
                tint = Color.Unspecified,
                contentDescription = null,
                modifier = Modifier.size(48.dp)
            )
        }
        PieChartLegend(
            entries = chartData.entries.take(3).map { it.label to it.color }
        )
    }
}

@Composable
private fun CategoryChartHeader(lastWeekExpenses: Double) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = stringResource(R.string.category_chart),
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 28.sp
            )
            Text(
                text = stringResource(R.string.last_7_days_expenses),
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 16.sp,
                lineHeight = 22.sp,
                color = outlineLight.copy(alpha = 0.7f)
            )
        }
        Text(
            text = lastWeekExpenses.formatMoney(isExpense = true, padding = 2),
            style = MaterialTheme.typography.bodyLarge,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 28.sp
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun CategoryPieChartSectionPreview() {
    WPayTheme {
        CategoryPieChartSection(
            modifier = Modifier.padding(16.dp),
            lastWeekExpenses = 600.0,
            chartData = pieChartData
        )
    }
}