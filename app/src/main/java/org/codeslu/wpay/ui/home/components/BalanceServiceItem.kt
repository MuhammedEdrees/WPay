package org.codeslu.wpay.ui.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.codeslu.wpay.R

@Composable
fun BalanceServiceItem(
    modifier: Modifier = Modifier,
    iconPainter: Painter,
    title: String,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .size(width = 98.dp, height = 68.dp)
            .clickable { onClick() },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = iconPainter,
            contentDescription = stringResource(R.string.service_description, title),
            modifier = modifier.size(32.dp)
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 14.sp,
            lineHeight = 19.sp
        )
    }
}