package org.codeslu.wpay.ui.home.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.codeslu.wpay.R
import org.codeslu.wpay.ui.theme.WPayTheme

@Composable
fun PromoItem(
    modifier: Modifier = Modifier,
    hasDiscount: Boolean,
    discountPercentage: Int,
    title: String,
    description: String,
) {
    Surface(
        modifier = modifier
            .size(width = 318.dp, height = 170.dp),
        shape = MaterialTheme.shapes.large,
        color = if (hasDiscount) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.tertiary,
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            PromoItemBackground(hasDiscount = hasDiscount)
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .widthIn(max = 168.dp)
            ) {
                if (hasDiscount) {
                    Text(
                        text = stringResource(R.string.discount_percentage, discountPercentage),
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = 14.sp,
                        lineHeight = 19.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Text(
                    modifier = Modifier.padding(top = 2.dp),
                    text = title,
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 20.sp,
                    lineHeight = 28.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = 12.sp,
                    lineHeight = 16.sp,
                )
            }
            if (hasDiscount) {
                Text(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(bottom = 26.dp, end = 18.dp),
                    text = "$discountPercentage%",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}

@Composable
private fun PromoItemBackground(
    modifier: Modifier = Modifier,
    hasDiscount: Boolean
){
    Box(
        modifier = modifier
        .size(width = 318.dp, height = 170.dp)
    ){
        if(hasDiscount){
            PromoWithDiscountBackground()
        } else {
            PromoWithoutDiscountBackground()
        }
    }
}

@Composable
private fun BoxScope.PromoWithDiscountBackground() {
    val rectangleColor = MaterialTheme.colorScheme.primary
    val circleColor = MaterialTheme.colorScheme.tertiary
    Canvas(
        modifier = Modifier.fillMaxSize().align(Alignment.BottomEnd)
    ) {
        rotate(degrees = -30f) {
            drawRoundRect(
                color = rectangleColor,
                size = Size(105.dp.toPx(), 125.dp.toPx()),
                cornerRadius = CornerRadius(12.dp.toPx()),
                topLeft = Offset(178.dp.toPx(), 120.dp.toPx())
            )
        }
        drawCircle(
            color = circleColor,
            radius = 12.dp.toPx(),
            center = Offset(277.dp.toPx(), 60.dp.toPx())
        )
    }
}

@Composable
private fun BoxScope.PromoWithoutDiscountBackground(){
    val topRectangleColor = MaterialTheme.colorScheme.secondary
    val bottomRectangleColor = MaterialTheme.colorScheme.primary
    val arcColor = MaterialTheme.colorScheme.onPrimary
    val capsuleColor = MaterialTheme.colorScheme.inversePrimary
    Canvas(
        modifier = Modifier.fillMaxSize().align(Alignment.BottomEnd)
    ) {
        rotate(degrees = -30f) {
            drawRoundRect(
                color = bottomRectangleColor,
                size = Size(105.dp.toPx(), 115.dp.toPx()),
                cornerRadius = CornerRadius(12.dp.toPx()),
                topLeft = Offset(190.dp.toPx(), 130.dp.toPx())
            )
        }
        rotate(degrees = -30f) {
            drawRoundRect(
                color = topRectangleColor,
                size = Size(105.dp.toPx(), 160.dp.toPx()),
                cornerRadius = CornerRadius(12.dp.toPx()),
                topLeft = Offset(230.dp.toPx(), 90.dp.toPx())
            )
        }
        drawArc(
            color = arcColor,
            startAngle = 0f,
            sweepAngle = 180f,
            useCenter = true,
            size = Size(32.dp.toPx(), 32.dp.toPx()),
            topLeft = Offset(220.dp.toPx(), 125.dp.toPx())
        )
        val topCapsuleTopLeftOffset = Offset(x = 245.dp.toPx(), y = 66.dp.toPx())
        rotate(degrees = 60f, pivot = topCapsuleTopLeftOffset) {
            drawRoundRect(
                color = capsuleColor,
                topLeft = topCapsuleTopLeftOffset,
                size = Size(11.dp.toPx(), 17.dp.toPx()),
                cornerRadius = CornerRadius(12.dp.toPx())
            )
        }
        val bottomCapsuleTopLeftOffset = Offset(x = 242.dp.toPx(), y = 72.dp.toPx())
        rotate(degrees = 90f, pivot = bottomCapsuleTopLeftOffset) {
            drawRoundRect(
                color = capsuleColor,
                topLeft = bottomCapsuleTopLeftOffset,
                size = Size(11.dp.toPx(), 17.dp.toPx()),
                cornerRadius = CornerRadius(12.dp.toPx())
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun PromoItemPreview() {
    WPayTheme {
        Column {
            PromoItem(
                modifier = Modifier.padding(16.dp),
                hasDiscount = false,
                discountPercentage = 30,
                title = "Black Friday deal Black Friday deal",
                description = "Get discount for every topup, transfer and payment"
            )
            PromoItemBackground(hasDiscount = false)
        }
    }
}