package org.codeslu.wpay.ui.statistics

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.codeslu.wpay.R
import org.codeslu.wpay.data.model.BarChartData
import org.codeslu.wpay.data.model.Expense
import org.codeslu.wpay.data.model.PieChartData
import org.codeslu.wpay.ui.notifications.components.OutlinedTopBarIcon
import org.codeslu.wpay.ui.statistics.components.CategoryPieChartSection
import org.codeslu.wpay.ui.statistics.components.IncomeAndExpensesCard
import org.codeslu.wpay.ui.statistics.components.IncomeAndExpensesChartSection
import org.codeslu.wpay.ui.statistics.components.RecentExpensesSection
import org.codeslu.wpay.ui.statistics.components.SegmentedBar
import org.codeslu.wpay.ui.theme.WPayTheme
import org.codeslu.wpay.ui.util.DummyValues.barChartData
import org.codeslu.wpay.ui.util.DummyValues.pieChartData
import org.codeslu.wpay.ui.util.DummyValues.recentExpenses
import org.koin.androidx.compose.koinViewModel

@Composable
fun StatisticsScreen(
    modifier: Modifier = Modifier,
    viewModel: StatisticsViewModel = koinViewModel(),
    onBackClicked: () -> Unit = {}
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    StatisticsScreenContent(
        totalIncome = state.totalIncome,
        totalExpenses = state.totalExpenses,
        lastWeekExpenses = state.lastWeekExpenses,
        selectedRange = state.selectedChartRange,
        barChartData = state.selectedBarChartData,
        pieChartData = state.pieChartData,
        recentExpenses = state.recentExpenses,
        onAction = { action ->
            when (action) {
                is StatisticsUiAction.OnBackClicked -> onBackClicked()
                else -> Unit
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun StatisticsScreenContent(
    modifier: Modifier = Modifier,
    onAction: (StatisticsUiAction) -> Unit = {},
    totalIncome: Double,
    totalExpenses: Double,
    lastWeekExpenses: Double,
    selectedRange: ChartRange,
    barChartData: BarChartData,
    pieChartData: PieChartData,
    recentExpenses: List<Expense>
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                title = {
                    Text(
                        text = stringResource(R.string.statistics),
                        style = MaterialTheme.typography.bodyLarge,
                        fontSize = 20.sp,
                        lineHeight = 28.sp
                    )
                },
                navigationIcon = {
                    OutlinedTopBarIcon(onClick = { onAction(StatisticsUiAction.OnBackClicked) }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                            contentDescription = "Navigate back",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                },
                actions = {
                    OutlinedTopBarIcon(onClick = { onAction(StatisticsUiAction.OnSettingsClicked) }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_settings),
                            contentDescription = "Settings",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            IncomeAndExpensesCard(totalIncome = totalIncome, totalExpenses = totalExpenses)
            IncomeAndExpensesChartSection(
                selectedRange = selectedRange,
                barChartData = barChartData,
                onUpdateRange = { onAction(StatisticsUiAction.OnChartRangeSelected(it)) }
            )
            SegmentedBar()
            CategoryPieChartSection(
                lastWeekExpenses = lastWeekExpenses,
                chartData = pieChartData
            )
            RecentExpensesSection(recentExpenses = recentExpenses)
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun StaticsScreenContentPreview() {
    WPayTheme {
        StatisticsScreenContent(
            totalIncome = 5440.0,
            totalExpenses = 2209.0,
            lastWeekExpenses = 312.0,
            selectedRange = ChartRange.WEEKLY,
            barChartData = barChartData,
            pieChartData = pieChartData,
            recentExpenses = recentExpenses
        )
    }
}