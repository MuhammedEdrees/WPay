package org.codeslu.wpay.ui.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.codeslu.wpay.R
import org.codeslu.wpay.data.model.Promo
import org.codeslu.wpay.ui.theme.WPayTheme

@Composable
fun PromoAndDiscountsSection(
    modifier: Modifier = Modifier,
    promos: List<Promo>,
    onSeeMoreClicked: () -> Unit,
) {
    val pagerState = rememberPagerState(pageCount = {
        promos.size
    })
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                text = stringResource(R.string.promo_discount),
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 18.sp,
                lineHeight = 25.sp,
                fontWeight = FontWeight.Bold
            )
            TextButton(
                onClick = { onSeeMoreClicked() },
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(
                    text = stringResource(R.string.see_more),
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 14.sp,
                    lineHeight = 19.sp,
                )
            }
        }
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 16.dp),
            pageSize = PageSize.Fixed(318.dp),
            pageSpacing = 16.dp
        ) { page ->
            val promo = promos[page]
            PromoItem(bannerImageLink = promo.bannerImageLink, discountPercentage = promo.discountPercentage, title = promo.title, description = promo.description)
        }
        if (promos.size > 1) {
            DotsIndicator(
                modifier = Modifier
                    .padding(vertical = 8.dp),
                totalDots = pagerState.pageCount,
                selectedIndex = pagerState.currentPage,
                selectedColor = MaterialTheme.colorScheme.primary,
                unSelectedColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
            )
        }
    }

}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun PromoSectionPreview() {
    WPayTheme {
        PromoAndDiscountsSection(promos = listOf(
            Promo(
                title = "Black Friday deal",
                description = "Get discount for every topup, transfer and payment\"",
                bannerImageLink = "",
                discountPercentage = 30
            ),
            Promo(
                title = "Special Offer for Today’s Top Up",
                description = "Get discount for every top up, transfer and payment",
                bannerImageLink = "",
                discountPercentage = 0
            )
        )) {
        }
    }
}