package org.codeslu.wpay.ui.statistics.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.codeslu.wpay.R
import org.codeslu.wpay.data.model.Expense
import org.codeslu.wpay.ui.theme.WPayTheme
import org.codeslu.wpay.ui.theme.onPrimaryContainerLight
import org.codeslu.wpay.ui.theme.segmentedBarBackground
import org.codeslu.wpay.ui.util.DummyValues.recentExpenses

@Composable
fun RecentExpensesSection(
    modifier: Modifier = Modifier,
    recentExpenses: List<Expense>,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = stringResource(R.string.recent_expenses),
            style = MaterialTheme.typography.bodyLarge,
            fontSize = 20.sp,
            lineHeight = 28.sp,
            fontWeight = FontWeight.Bold
        )
        recentExpenses.forEachIndexed { index, expense ->
            RecentExpenseItem(
                recentExpense = expense
            )
            if (index < recentExpenses.size - 1) {
                HorizontalDivider(
                    thickness = 1.dp,
                    color = segmentedBarBackground
                )
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun RecentExpensesSectionPreview() {
    WPayTheme {

        RecentExpensesSection(
            modifier = Modifier.padding(16.dp),
            recentExpenses = recentExpenses
        )
    }

}