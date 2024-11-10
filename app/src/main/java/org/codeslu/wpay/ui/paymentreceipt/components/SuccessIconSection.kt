package org.codeslu.wpay.ui.paymentreceipt.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import org.codeslu.wpay.R

@Composable
fun SuccessIconSection(
    modifier: Modifier = Modifier
) {
    Image(
        modifier = modifier.size(100.dp),
        painter = painterResource(id = R.drawable.ic_success),
        contentDescription = null,
    )
}