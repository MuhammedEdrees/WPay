package org.codeslu.wpay.ui.summarytransaction.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.codeslu.wpay.R
import org.codeslu.wpay.data.model.Card
import org.codeslu.wpay.ui.theme.onPrimaryLight
import org.codeslu.wpay.ui.theme.outlineLight
import org.codeslu.wpay.ui.theme.outlineVariantLight
import org.codeslu.wpay.ui.theme.primaryContainerLight

@Composable
fun CardBottomSheet(
    modifier: Modifier = Modifier,
    cards: List<Card>,
    onProceedClicked: () -> Unit
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 158.dp),
        shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(width = 48.dp, height = 5.dp)
                    .background(
                        color = outlineVariantLight,
                        shape = RoundedCornerShape(3.dp)
                    )
            )
            Text(
                text = stringResource(R.string.choose_cards),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                lineHeight = 25.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )
            cards.forEach { card ->
                CardItem(
                    card = card,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                )
            }
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 54.dp)
                    .padding(top = 24.dp),
                shape = RoundedCornerShape(16.dp),
                contentPadding = PaddingValues(vertical = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = primaryContainerLight,
                    contentColor = onPrimaryLight
                ),
                onClick = { onProceedClicked() }
            ) {
                Text(
                    text = "Proceed to Pay",
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 16.sp,
                    lineHeight = 22.sp,
                )
            }
        }
    }
}

