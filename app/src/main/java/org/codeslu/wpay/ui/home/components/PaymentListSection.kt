package org.codeslu.wpay.ui.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.codeslu.wpay.R
import org.codeslu.wpay.ui.theme.WPayTheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PaymentListSection(
    modifier: Modifier = Modifier,
    onOptionClicked: (Int) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(245.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = stringResource(R.string.payment_list),
            style = MaterialTheme.typography.bodyLarge,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 25.sp
        )
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            maxItemsInEachRow = 4
        ) {
            PaymentOptions.entries.forEach { item ->
                PaymentOptionItem(
                    iconPainter = painterResource(id = item.iconDrawable),
                    title = stringResource(id = item.titleRes),
                    onClick = { onOptionClicked(item.titleRes) }
                )
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun PaymentListPreview() {
    WPayTheme {
        PaymentListSection(modifier = Modifier.padding(16.dp)) {}
    }
}

