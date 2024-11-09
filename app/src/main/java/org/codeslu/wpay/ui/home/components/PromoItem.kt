package org.codeslu.wpay.ui.home.components

import androidx.compose.foundation.layout.Box
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
    bannerImageLink: String,
    discountPercentage: Int,
    title: String,
    description: String,
) {
    Surface(
        modifier = modifier
            .size(width = 318.dp, height = 170.dp),
        shape = MaterialTheme.shapes.large,
        color = MaterialTheme.colorScheme.primary
    ) {
//        StatefulAsyncImage(
//            modifier = Modifier
//                .fillMaxSize()
//                .align(Alignment.Center),
//            imageUrl = bannerImageLink,
//            shape = MaterialTheme.shapes.large
//        )
        Box(
            modifier = Modifier.fillMaxSize()
        ) {

            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .widthIn(max = 168.dp)
            ) {
                if (discountPercentage > 0) {
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
            if (discountPercentage > 0) {
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

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun PromoItemPreview() {
    WPayTheme {
        PromoItem(
            modifier = Modifier.padding(16.dp),
            bannerImageLink = "",
            discountPercentage = 30,
            title = "Black Friday deal",
            description = "Get discount for every topup, transfer and payment"
        )
    }
}