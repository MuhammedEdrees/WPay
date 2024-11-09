package org.codeslu.wpay.ui.statistics

import androidx.annotation.StringRes
import org.codeslu.wpay.R
import org.codeslu.wpay.data.model.BarChartData
import org.codeslu.wpay.data.model.Expense
import org.codeslu.wpay.data.model.PieChartData

data class StatisticsUiState(
    val totalExpenses: Double = 0.0,
    val totalIncome: Double = 0.0,
    val selectedChartRange: ChartRange = ChartRange.WEEKLY,
    val weeklyData: BarChartData = BarChartData(),
    val lastWeekExpenses: Double = 0.0,
    val pieChartData: PieChartData = PieChartData() ,
    val monthlyData: BarChartData = BarChartData(),
    val recentExpenses: List<Expense> = emptyList()
) {
    val selectedBarChartData: BarChartData
        get() = when (selectedChartRange) {
            ChartRange.WEEKLY -> weeklyData
            ChartRange.MONTHLY -> monthlyData
        }
}

enum class ChartRange(@StringRes val displayTitleRes: Int) {
    WEEKLY(R.string.weekly), MONTHLY(R.string.monthly)
}