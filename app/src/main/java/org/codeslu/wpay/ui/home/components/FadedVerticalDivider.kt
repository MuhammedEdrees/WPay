package org.codeslu.wpay.ui.home.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun FadedVerticalDivider(
    modifier: Modifier = Modifier,
    thickness: Dp = 1.dp,
    height: Dp = 57.dp,
    color: Color = MaterialTheme.colorScheme.onSecondary
) {
    Canvas(
        modifier = modifier
            .width(thickness)
            .height(height)
            .alpha(0.3f)
    ) {
        drawRect(
            brush = Brush.verticalGradient(
                colors = listOf(
                    color.copy(alpha = 0f),
                    color,
                    color.copy(alpha = 0f)
                ),
                startY = 0f,
                endY = size.height
            )
        )
    }
}