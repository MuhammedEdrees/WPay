package org.codeslu.wpay.ui.statistics.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.codeslu.wpay.R
import org.codeslu.wpay.data.model.BarChartData
import org.codeslu.wpay.ui.statistics.ChartRange
import org.codeslu.wpay.ui.theme.WPayTheme

@Composable
fun IncomeAndExpensesChartSection(
    modifier: Modifier = Modifier,
    selectedRange: ChartRange,
    barChartData: BarChartData,
    onUpdateRange: (ChartRange) -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ChartRangeDropDownMenu(
                modifier = Modifier.weight(1f),
                selectedRange = selectedRange,
                onUpdateRange = onUpdateRange
            )
            ChartLegend()
        }
        BarChart(
            modifier = Modifier.fillMaxWidth(),
            barChartData = barChartData
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ChartRangeDropDownMenu(
    modifier: Modifier = Modifier,
    selectedRange: ChartRange,
    onUpdateRange: (ChartRange) -> Unit
) {
    var isDropDownMenuExpanded by remember {
        mutableStateOf(false)
    }
    ExposedDropdownMenuBox(
        expanded = isDropDownMenuExpanded,
        onExpandedChange = { isDropDownMenuExpanded = !isDropDownMenuExpanded },
        modifier = modifier.size(width = 91.dp, height = 28.dp)
    ) {
        FilledTonalButton(
            modifier = Modifier.size(width = 91.dp, height = 28.dp).menuAnchor(),
            contentPadding = PaddingValues(horizontal = 8.dp),
            onClick = { isDropDownMenuExpanded = true }) {
            Text(
                text = stringResource(id = selectedRange.displayTitleRes),
                style = MaterialTheme.typography.bodySmall,
                fontSize = 14.sp,
                lineHeight = 19.sp
            )
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = null,
                modifier = Modifier
                    .size(16.dp)
                    .padding(start = 4.dp)
            )
        }

        ExposedDropdownMenu(
            expanded = isDropDownMenuExpanded,
            onDismissRequest = {
                isDropDownMenuExpanded = false
            }
        ) {
            ChartRange.entries.forEach { range ->
                DropdownMenuItem(
                    text = { Text(text = stringResource(id = range.displayTitleRes)) },
                    onClick = {
                        isDropDownMenuExpanded = false
                        onUpdateRange(range)
                    }
                )
            }
        }
    }
}

@Composable
private fun ChartLegend(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        ChartLegendItem(icon = R.drawable.ic_arrow_down, title = R.string.income)
        ChartLegendItem(icon = R.drawable.ic_arrow_up, title = R.string.expense)
    }
}

@Composable
fun ChartLegendItem(
    modifier: Modifier = Modifier,
    @DrawableRes
    icon: Int,
    @StringRes
    title: Int,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier
                .size(24.dp)
                .padding(vertical = 9.dp, horizontal = 6.dp)
        )
        Text(
            text = stringResource(id = title),
            style = MaterialTheme.typography.bodySmall,
            fontSize = 14.sp,
            lineHeight = 19.sp
        )

    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun ChartPreview() {
    WPayTheme {
        IncomeAndExpensesChartSection(
            modifier = Modifier.padding(16.dp),
            selectedRange = ChartRange.WEEKLY,
            barChartData = BarChartData(emptyList(), ChartRange.WEEKLY),
            onUpdateRange = {}
        )
    }
}