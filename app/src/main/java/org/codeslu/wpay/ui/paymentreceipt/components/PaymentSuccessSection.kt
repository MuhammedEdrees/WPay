package org.codeslu.wpay.ui.paymentreceipt.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.codeslu.wpay.R
import org.codeslu.wpay.ui.theme.notificationHeaderColor

@Composable
fun PaymentSuccessSection(
    modifier: Modifier = Modifier,
    expenseTitle: String,
) {
    Column(
        modifier = modifier.padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(R.string.payment_success),
            style = MaterialTheme.typography.headlineMedium,
            fontSize = 24.sp,
            lineHeight = 33.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = stringResource(
                R.string.your_payment_for_has_been_successfully_done,
                expenseTitle
            ),
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 16.sp,
            lineHeight = 22.sp,
            color = notificationHeaderColor,
            textAlign = TextAlign.Center
        )
    }
}