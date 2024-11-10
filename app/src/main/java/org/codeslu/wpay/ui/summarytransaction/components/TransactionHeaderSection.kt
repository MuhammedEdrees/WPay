package org.codeslu.wpay.ui.summarytransaction.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.datetime.LocalDateTime
import org.codeslu.wpay.R
import org.codeslu.wpay.ui.theme.WPayTheme
import org.codeslu.wpay.ui.theme.onPrimaryLight
import org.codeslu.wpay.ui.theme.orange1Light
import org.codeslu.wpay.ui.theme.primaryLight
import org.codeslu.wpay.ui.theme.surfaceContainerHighLight
import org.codeslu.wpay.ui.util.LocalDateTimeUtil.formatDate
import org.codeslu.wpay.ui.util.LocalDateTimeUtil.now

@Composable
fun TransactionHeaderSection(
    modifier: Modifier = Modifier,
    @DrawableRes
    iconRes: Int,
    iconBackgroundColor: Color,
    title: String,
    date: LocalDateTime,
    ) {
    Column (
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier
                .size(80.dp)
                .background(color = iconBackgroundColor, shape = RoundedCornerShape(24.dp))
                .padding(16.dp)
        )
        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = onPrimaryLight,
            fontSize = 24.sp,
            lineHeight = 33.sp,
            modifier = Modifier.padding(top = 16.dp)
        )
        Text(
            text = stringResource(R.string.payment_on, date.formatDate()),
            style = MaterialTheme.typography.bodySmall,
            color = orange1Light,
            fontSize = 14.sp,
            lineHeight = 19.sp,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun TransactionHeaderPreview() {
    WPayTheme {
        TransactionHeaderSection(
            modifier = Modifier.background(primaryLight).fillMaxWidth().padding(16.dp),
            iconRes = R.drawable.ic_starbucks,
            iconBackgroundColor = surfaceContainerHighLight ,
            title = "Starbucks Coffee",
            date = LocalDateTime.now()
        )
    }
}