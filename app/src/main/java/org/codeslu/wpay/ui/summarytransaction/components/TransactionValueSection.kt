package org.codeslu.wpay.ui.summarytransaction.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.codeslu.wpay.R
import org.codeslu.wpay.ui.theme.WPayTheme
import org.codeslu.wpay.ui.theme.backgroundLight
import org.codeslu.wpay.ui.theme.onPrimaryLight
import org.codeslu.wpay.ui.theme.primaryLight
import org.codeslu.wpay.ui.util.StringUtils.formatMoney

@Composable
fun TransactionValueSection(
    modifier: Modifier = Modifier,
    totalAmount: Double,
    paymentFee: Double,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = totalAmount.formatMoney(padding = 2),
            style = MaterialTheme.typography.headlineLarge,
            fontSize = 48.sp,
            lineHeight = 68.sp,
            fontWeight = FontWeight.Bold,
            color= onPrimaryLight,
        )
        PaymentFeeCard(paymentFee)
    }
}

@Composable
private fun PaymentFeeCard(paymentFee: Double) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = backgroundLight.copy(alpha = 0.1f),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_info),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.size(16.dp)
            )
            Text(
                text = stringResource(
                    R.string.payment_fee_has_been_applied,
                    paymentFee.formatMoney()
                ),
                style = MaterialTheme.typography.bodySmall,
                fontSize = 14.sp,
                lineHeight = 19.sp,
                color = onPrimaryLight
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun TransactionValueSectionPreview() {
    WPayTheme {
        TransactionValueSection(
            modifier = Modifier.background(primaryLight).fillMaxWidth().padding(16.dp),
            totalAmount = 15.0,
            paymentFee = 2.0
        )
    }
}