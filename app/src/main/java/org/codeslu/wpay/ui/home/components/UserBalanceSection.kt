package org.codeslu.wpay.ui.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.codeslu.wpay.R
import org.codeslu.wpay.ui.theme.WPayTheme
import org.codeslu.wpay.ui.util.StringUtils.formatMoney

@Composable
fun UserBalanceSection(
    modifier: Modifier = Modifier,
    firstName: String,
    balance: Double,
    onTransferClicked: () -> Unit = {},
    onTopUpClicked: () -> Unit = {},
    onHistoryClicked: () -> Unit = {}
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        NameAndBalanceSection(
            firstName = firstName,
            availableBalance = balance
        )
        BalanceServicesSection(
            onTransferClicked = onTransferClicked,
            onTopUpClicked = onTopUpClicked,
            onHistoryClicked = onHistoryClicked
        )
    }

}

@Composable
private fun NameAndBalanceSection(
    modifier: Modifier = Modifier,
    firstName: String,
    availableBalance: Double
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = stringResource(R.string.hello_first_name, firstName),
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 25.sp
            )
            Text(
                text = stringResource(R.string.your_available_balance),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.4f),
                fontSize = 14.sp,
                lineHeight = 19.sp
            )
        }
        Text(
            text = availableBalance.formatMoney(),
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            lineHeight = 39.sp
        )

    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun UserBalanceSectionPreview() {
    WPayTheme {
        UserBalanceSection(
            modifier = Modifier.padding(16.dp),
            firstName = "Andre",
            balance = 15901.0,
        )
    }
}