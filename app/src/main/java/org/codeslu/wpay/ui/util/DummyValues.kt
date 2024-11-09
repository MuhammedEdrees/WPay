package org.codeslu.wpay.ui.util

import androidx.compose.ui.graphics.Color
import org.codeslu.wpay.R
import org.codeslu.wpay.data.model.BarChartData
import org.codeslu.wpay.data.model.ChartDataEntry
import org.codeslu.wpay.data.model.Expense
import org.codeslu.wpay.data.model.PieChartData
import org.codeslu.wpay.data.model.PieChartEntry
import org.codeslu.wpay.ui.statistics.ChartRange
import org.codeslu.wpay.ui.theme.onPrimaryContainerLight
import org.codeslu.wpay.ui.theme.orange1Light
import org.codeslu.wpay.ui.theme.primaryContainerLight
import org.codeslu.wpay.ui.theme.secondaryContainerLight

object DummyValues {
    val barChartData = BarChartData(
        rangeType = ChartRange.WEEKLY,
        dataEntries = listOf(
            ChartDataEntry(
                title = "Sun",
                income = 100.0,
                expenses = 50.0
            ),
            ChartDataEntry(
                title = "Mon",
                income = 20.0,
                expenses = 100.0
            ),
            ChartDataEntry(
                title = "Tue",
                income = 400.0,
                expenses = 150.0
            ),
            ChartDataEntry(
                title = "Wed",
                income = 200.0,
                expenses = 200.0
            ),
            ChartDataEntry(
                title = "Thu",
                income = 610.0,
                expenses = 250.0
            ),
            ChartDataEntry(
                title = "Fri",
                income = 800.0,
                expenses = 300.0
            ),
            ChartDataEntry(
                title = "Sat",
                income = 150.0,
                expenses = 350.0
            )
        )
    )
    val pieChartData = PieChartData(
        listOf(
            PieChartEntry(
                value = 150.0,
                color = primaryContainerLight,
                label = "Shopping"
            ),
            PieChartEntry(
                value = 300.0,
                color = orange1Light,
                label = "Coffee"
            ),
            PieChartEntry(
                value = 150.0,
                color = secondaryContainerLight,
                label = "Transportation"
            ),
        )
    )
    val recentExpenses = listOf(
        Expense(
            title = "Starbucks Coffee",
            value = 156.0,
            iconRes = R.drawable.ic_starbucks,
            backgroundColor = onPrimaryContainerLight,
        ),
        Expense(
            title = "Netflix Subscription",
            value = 87.0,
            iconRes = R.drawable.ic_netflix,
            backgroundColor = Color(0xFF030319),
        ),
    )
}