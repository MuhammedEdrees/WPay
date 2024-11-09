package org.codeslu.wpay.data.model

import org.codeslu.wpay.ui.statistics.ChartRange


data class BarChartData(
    val dataEntries: List<ChartDataEntry> = emptyList(),
    val rangeType: ChartRange = ChartRange.WEEKLY
)
