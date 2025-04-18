package org.codeslu.wpay.ui.util

import androidx.compose.ui.graphics.Color
import kotlinx.datetime.LocalDateTime
import org.codeslu.wpay.R
import org.codeslu.wpay.data.model.BarChartData
import org.codeslu.wpay.data.model.Card
import org.codeslu.wpay.data.model.ChartDataEntry
import org.codeslu.wpay.data.model.Expense
import org.codeslu.wpay.data.model.PieChartData
import org.codeslu.wpay.data.model.PieChartEntry
import org.codeslu.wpay.data.model.Transaction
import org.codeslu.wpay.ui.statistics.ChartRange
import org.codeslu.wpay.ui.theme.onPrimaryContainerLight
import org.codeslu.wpay.ui.theme.orange1Light
import org.codeslu.wpay.ui.theme.primaryContainerLight
import org.codeslu.wpay.ui.theme.secondaryContainerLight
import org.codeslu.wpay.ui.theme.surfaceContainerHighLight
import org.codeslu.wpay.ui.util.LocalDateTimeUtil.now

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
                value = 100.0,
                color = primaryContainerLight,
                label = "Shopping"
            ),
            PieChartEntry(
                value = 72.0,
                color = orange1Light,
                label = "Coffee"
            ),
            PieChartEntry(
                value = 140.0,
                color = secondaryContainerLight,
                label = "Transportation"
            ),
        )
    )
    val recentExpenses = listOf(
        Expense(
            title = "Starbucks Coffee",
            amount = 156.0,
            iconRes = R.drawable.ic_starbucks,
            backgroundColor = onPrimaryContainerLight,
        ),
        Expense(
            title = "Netflix Subscription",
            amount = 87.0,
            iconRes = R.drawable.ic_netflix,
            backgroundColor = Color(0xFF030319),
        ),
    )
    val transaction = Transaction(
        title = "Starbucks Coffee",
        iconRes = R.drawable.ic_starbucks,
        iconBackgroundColor = surfaceContainerHighLight,
        date = LocalDateTime.now(),
        amount = 15.0,
        paymentFee = 2.0
    )
    val card = Card(
        title = "Wally Virtual Card",
        iconRes = R.drawable.ic_logo,
        number = "0318-1608-2105"
    )
}