package org.codeslu.wpay.ui.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.codeslu.wpay.R
import org.codeslu.wpay.ui.theme.onPrimaryLight
import org.codeslu.wpay.ui.theme.primaryContainerLight

@Composable
fun BalanceServicesSection(
    modifier: Modifier = Modifier,
    onTransferClicked: () -> Unit = {},
    onTopUpClicked: () -> Unit = {},
    onHistoryClicked: () -> Unit = {}
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(84.dp),
        shape = RoundedCornerShape(16.dp),
        color = primaryContainerLight,
        contentColor = onPrimaryLight
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BalanceServiceItem(
                iconPainter = painterResource(id = R.drawable.ic_transfer),
                title = stringResource(id = R.string.transfer),
                onClick = onTransferClicked
            )
            FadedVerticalDivider()
            BalanceServiceItem(
                iconPainter = painterResource(id = R.drawable.ic_topup),
                title = stringResource(id = R.string.top_up),
                onClick = onTopUpClicked
            )
            FadedVerticalDivider()
            BalanceServiceItem(
                iconPainter = painterResource(id = R.drawable.ic_history),
                title = stringResource(id = R.string.history),
                onClick = onHistoryClicked
            )
        }
    }
}