package org.codeslu.wpay.ui.statistics

sealed interface StatisticsUiAction {
    data object OnBackClicked : StatisticsUiAction
    data object OnSettingsClicked : StatisticsUiAction
    data class OnChartRangeSelected(val chartRange: ChartRange) : StatisticsUiAction

}