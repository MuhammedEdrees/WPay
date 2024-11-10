package org.codeslu.wpay.ui.paymentreceipt.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.codeslu.wpay.R
import org.codeslu.wpay.ui.theme.notificationHeaderColor
import org.codeslu.wpay.ui.util.StringUtils.formatMoney

@Composable
fun TotalPaymentSection(
    modifier: Modifier = Modifier,
    amount: Double
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = stringResource(R.string.total_payment),
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 16.sp,
            lineHeight = 22.sp,
            color = notificationHeaderColor
        )
        Text(
            text = amount.formatMoney(padding = 2),
            style = MaterialTheme.typography.headlineMedium,
            fontSize = 32.sp,
            lineHeight = 44.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}