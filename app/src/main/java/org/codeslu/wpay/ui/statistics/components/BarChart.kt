package org.codeslu.wpay.ui.statistics.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.codeslu.wpay.data.model.BarChartData
import org.codeslu.wpay.data.model.ChartDataEntry
import org.codeslu.wpay.ui.statistics.ChartRange
import org.codeslu.wpay.ui.theme.WPayTheme
import org.codeslu.wpay.ui.util.DummyValues.barChartData

@Composable
fun BarChart(
    modifier: Modifier = Modifier,
    barChartData: BarChartData
) {
    Row (
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        barChartData.dataEntries.forEach { entry ->
            BarItem(
                chartEntryTitle = entry.title,
                income = entry.income,
                expenses = entry.expenses
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun BarChartPreview() {
    WPayTheme {
        BarChart(
            modifier = Modifier.padding(16.dp),
            barChartData = barChartData
        )
    }
}
