package org.codeslu.wpay.ui.notifications.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun OutlinedTopBarIcon(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    iconContent: @Composable () -> Unit
) {
    OutlinedIconButton(
        onClick = { onClick() },
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(width = 2.dp, color = Color.Black.copy(alpha = 0.1f)),
        modifier = modifier.size(40.dp)
    ) {
        iconContent()
    }
}