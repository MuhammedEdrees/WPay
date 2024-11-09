package org.codeslu.wpay.ui.home.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.codeslu.wpay.ui.theme.WPayTheme

@Composable
fun DotsIndicator(
    modifier: Modifier = Modifier,
    totalDots: Int,
    selectedIndex: Int,
    selectedColor: Color,
    unSelectedColor: Color,
) {
    LazyRow(
        modifier = modifier
            .wrapContentWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.spacedBy(2.dp)

    ) {
        items(totalDots) { index ->
            val isSelected = index == selectedIndex
            val width = if (isSelected) 30.dp else 8.dp
            val color = if (isSelected) selectedColor else unSelectedColor
            Box(
                modifier = Modifier
                    .size(width = width, height = 8.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(color)
                    .animateContentSize(spring())
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun DotsIndicatorPreview() {
    WPayTheme {
        DotsIndicator(
            modifier = Modifier.padding(16.dp),
            totalDots = 5,
            selectedIndex = 2,
            selectedColor = Color.Red,
            unSelectedColor = Color.Gray
        )
    }
}