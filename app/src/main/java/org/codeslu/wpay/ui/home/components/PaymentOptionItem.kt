package org.codeslu.wpay.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.codeslu.wpay.R
import org.codeslu.wpay.ui.theme.surfaceContainerLowLight

@Composable
fun PaymentOptionItem(
    modifier: Modifier = Modifier,
    iconPainter: Painter,
    title: String,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .width(74.dp)
            .clickable { onClick() },
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = iconPainter,
            contentDescription = stringResource(R.string.title_option, title),
            tint = Color.Unspecified,
            modifier = Modifier
                .size(56.dp)
                .background(surfaceContainerLowLight, RoundedCornerShape(16.dp))
                .padding(16.dp)
        )
        Text(
            text = title,
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center,
            fontSize = 14.sp,
            lineHeight = 19.sp
        )
    }
}