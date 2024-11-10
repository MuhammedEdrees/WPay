package org.codeslu.wpay.ui.summarytransaction.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.codeslu.wpay.R
import org.codeslu.wpay.data.model.Card
import org.codeslu.wpay.ui.theme.WPayTheme
import org.codeslu.wpay.ui.theme.backgroundLight
import org.codeslu.wpay.ui.theme.notificationHeaderColor
import org.codeslu.wpay.ui.theme.onBackgroundLight
import org.codeslu.wpay.ui.theme.segmentedBarBackground
import org.codeslu.wpay.ui.util.DummyValues

@Composable
fun CardItem(
    modifier: Modifier = Modifier,
    card: Card
) {
    ListItem(
        modifier = modifier.clip(RoundedCornerShape(16.dp)),
        colors = ListItemDefaults.colors(
            containerColor = segmentedBarBackground,
            headlineColor = onBackgroundLight,
            supportingColor = notificationHeaderColor,
        ),
        leadingContent = {
            Icon(
                painter = painterResource(id = card.iconRes),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier
                    .size(width = 48.dp, height = 40.dp)
                    .background(color = backgroundLight, shape = RoundedCornerShape(8.dp))
                    .padding(vertical = 6.dp)
            )
        },
        headlineContent = {
            Text(
                text = card.title,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                lineHeight = 19.sp
            )
        },
        supportingContent = {
            Text(
                text = card.number,
                style = MaterialTheme.typography.bodySmall,
                fontSize = 12.sp,
                lineHeight = 16.sp
            )
        },
        trailingContent = {
            Icon(
                painter = painterResource(id = R.drawable.ic_expand),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.size(24.dp)
            )
        }
    )
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun CardItemPreview() {
    WPayTheme {
        CardItem(card = DummyValues.card, modifier = Modifier.padding(16.dp))
    }
}