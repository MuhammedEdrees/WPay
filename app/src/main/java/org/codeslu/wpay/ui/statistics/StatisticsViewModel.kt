package org.codeslu.wpay.ui.statistics

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.codeslu.wpay.ui.util.DummyValues.barChartData
import org.codeslu.wpay.ui.util.DummyValues.pieChartData
import org.codeslu.wpay.ui.util.DummyValues.recentExpenses

class StatisticsViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(StatisticsUiState())
    val uiState = _uiState.asStateFlow()
    init{
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    totalIncome = 5440.0,
                    totalExpenses = 2209.0,
                    lastWeekExpenses = 312.0,
                    selectedChartRange = ChartRange.WEEKLY,
                    weeklyData = barChartData,
                    pieChartData = pieChartData,
                    recentExpenses = recentExpenses
                )
            }
        }
    }
}