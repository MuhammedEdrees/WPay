package org.codeslu.wpay.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import org.codeslu.wpay.R
import org.codeslu.wpay.app.navigation.Route
import org.codeslu.wpay.ui.theme.backgroundLight
import org.codeslu.wpay.ui.theme.greenBackgroundLight

@Composable
fun BackgroundContainer(
    modifier: Modifier = Modifier,
    currentRoute: String,
    content: @Composable () -> Unit
) {
    Box(modifier = modifier) {
        Background(currentRoute)
        content()
    }
}

@Composable
private fun Background(currentRoute: String) {
    when (currentRoute) {
        in listOf(Route.NotificationsScreenRoute, Route.SummaryTransactionScreenRoute).map { it::class.qualifiedName.orEmpty() } -> {
            StaticColorWithHeaderBackground(
                color = greenBackgroundLight,
                headerRes = R.drawable.bg_mask_group1
            )
        }

        Route.ScanToPayScreenRoute::class.qualifiedName.orEmpty() -> {
            StaticColorBackground(color = greenBackgroundLight)
        }

        Route.PaymentReceiptScreenRoute::class.qualifiedName.orEmpty() -> {
            StaticColorWithHeaderBackground(
                color = greenBackgroundLight,
                headerRes = R.drawable.bg_ribbons
            )
        }

        else -> {
            StaticColorBackground(color = backgroundLight)
        }
    }
}

@Composable
fun StaticColorWithHeaderBackground(
    color: Color,
    @DrawableRes
    headerRes: Int
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = color),
        contentAlignment = Alignment.TopCenter
    ) {
        Image(
            painter = painterResource(id = headerRes),
            contentDescription = null,
        )
    }
}

@Composable
private fun StaticColorBackground(color: Color) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = color)
    )
}