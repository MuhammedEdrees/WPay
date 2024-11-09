package org.codeslu.wpay.ui.statistics.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.codeslu.wpay.R
import org.codeslu.wpay.ui.home.components.FadedVerticalDivider
import org.codeslu.wpay.ui.theme.WPayTheme
import org.codeslu.wpay.ui.theme.onPrimaryContainerLight
import org.codeslu.wpay.ui.theme.onPrimaryLight
import org.codeslu.wpay.ui.theme.surfaceVariantLight
import org.codeslu.wpay.ui.util.StringUtils.formatMoney

@Composable
fun IncomeAndExpensesCard(
    modifier: Modifier = Modifier,
    totalIncome: Double,
    totalExpenses: Double
) {
    Surface (
        modifier = modifier
            .fillMaxWidth()
            .height(90.dp),
        color = onPrimaryContainerLight,
        contentColor = onPrimaryLight,
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            IncomeExpenseCardSection(title = stringResource(R.string.income), amount = totalIncome)
            FadedVerticalDivider(modifier = Modifier.padding(horizontal = 40.dp))
            IncomeExpenseCardSection(title = stringResource(R.string.expense), amount = totalExpenses)
        }
    }
}

@Composable
fun IncomeExpenseCardSection(
    modifier: Modifier = Modifier,
    title: String,
    amount: Double
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ){
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 14.sp,
            lineHeight = 19.sp,
            color = surfaceVariantLight
        )
        Text(
            text = amount.formatMoney(),
            style = MaterialTheme.typography.headlineMedium,
            fontSize = 24.sp,
            lineHeight = 33.sp,
            color = onPrimaryLight)
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun IncomeAndExpensesCardPreview() {
    WPayTheme {
        IncomeAndExpensesCard(modifier = Modifier.padding(horizontal = 16.dp), totalIncome = 5440.0, totalExpenses = 2209.0)
    }
}