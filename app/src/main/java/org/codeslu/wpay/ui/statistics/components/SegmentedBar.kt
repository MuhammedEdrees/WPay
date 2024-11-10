package org.codeslu.wpay.ui.statistics.components

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.codeslu.wpay.R
import org.codeslu.wpay.ui.theme.WPayTheme
import org.codeslu.wpay.ui.theme.backgroundLight
import org.codeslu.wpay.ui.theme.onPrimaryContainerLight
import org.codeslu.wpay.ui.theme.onSecondaryLight
import org.codeslu.wpay.ui.theme.outlineVariantLight
import org.codeslu.wpay.ui.theme.primaryLight
import org.codeslu.wpay.ui.theme.segmentedBarBackground
import org.codeslu.wpay.ui.theme.surfaceVariantLight
import kotlin.math.exp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SegmentedBar(modifier: Modifier = Modifier) {
    var selectedSegment by remember{
        mutableStateOf("Expenses")
    }
    val incomeSegment = stringResource(id = R.string.income)
    val expensesSegment = stringResource(id = R.string.expenses)
    Surface(
        shape = RoundedCornerShape(50.dp),
        color = segmentedBarBackground,
        modifier = modifier
            .fillMaxWidth()
            .height(54.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp)
                .padding(2.dp)
        ){
            SegmentButton(
                modifier = Modifier.weight(1f),
                enabled = selectedSegment == incomeSegment,
                onClick = { selectedSegment = incomeSegment },
                title = incomeSegment
            )
            SegmentButton(
                modifier = Modifier.weight(1f),
                enabled = selectedSegment == expensesSegment,
                onClick = { selectedSegment = expensesSegment },
                title = expensesSegment
            )

        }

    }
}

@Composable
fun SegmentButton(
    modifier: Modifier = Modifier,
    enabled: Boolean,
    onClick: () -> Unit,
    title: String
) {
    Crossfade(
        modifier = modifier,
        targetState = enabled
    ) { state ->
        when {
            state -> {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = onPrimaryContainerLight,
                        containerColor = onSecondaryLight
                    ),
                    onClick = { onClick() }) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 16.sp,
                        lineHeight = 22.sp
                    )
                }
            }

            else -> {
                TextButton(
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.textButtonColors(contentColor = outlineVariantLight),
                    onClick = { onClick() }
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 16.sp,
                        lineHeight = 22.sp
                    )

                }
            }
        }

    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun SegmentedBarPreview() {
    WPayTheme {
        SegmentedBar()
    }
}