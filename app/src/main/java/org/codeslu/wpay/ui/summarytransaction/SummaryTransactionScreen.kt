package org.codeslu.wpay.ui.summarytransaction

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.codeslu.wpay.R
import org.codeslu.wpay.data.model.Card
import org.codeslu.wpay.data.model.Transaction
import org.codeslu.wpay.ui.notifications.components.OutlinedTopBarIcon
import org.codeslu.wpay.ui.components.TransparentCenterAlignedTopAppBar
import org.codeslu.wpay.ui.summarytransaction.components.CardBottomSheet
import org.codeslu.wpay.ui.summarytransaction.components.TransactionHeaderSection
import org.codeslu.wpay.ui.summarytransaction.components.TransactionValueSection
import org.codeslu.wpay.ui.theme.WPayTheme
import org.codeslu.wpay.ui.theme.onPrimaryLight
import org.codeslu.wpay.ui.theme.primaryLight
import org.codeslu.wpay.ui.util.DummyValues
import org.codeslu.wpay.ui.util.DummyValues.transaction
import org.koin.androidx.compose.koinViewModel

@Composable
fun SummaryTransactionScreen(
    modifier: Modifier = Modifier,
    onBackClicked: () -> Unit = {},
    onProceedClicked: () -> Unit = {},
    viewModel: SummaryTransactionViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    SummaryTransactionScreenContent(
        modifier = modifier,
        onAction = { action ->
            when(action){
                SummaryTransactionUiAction.OnBackClicked -> onBackClicked()
                SummaryTransactionUiAction.OnProceedClicked -> onProceedClicked()
                else -> Unit
            }
        },
        transaction = state.transaction,
        cards = state.cards
    )
}

@Composable
private fun SummaryTransactionScreenContent(
    modifier: Modifier = Modifier,
    onAction: (SummaryTransactionUiAction) -> Unit,
    transaction: Transaction,
    cards: List<Card>
) {
    Scaffold(
        modifier = modifier,
        containerColor = Color.Transparent,
        contentColor = onPrimaryLight,
        topBar = {
            TransparentCenterAlignedTopAppBar(
                navigationIcon = {
                    OutlinedTopBarIcon(onClick = { onAction(SummaryTransactionUiAction.OnBackClicked) }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                            contentDescription = "Navigate back",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                },
                title = {
                    Text(
                        text = stringResource(R.string.summary_transaction),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyLarge,
                        fontSize = 20.sp,
                        lineHeight = 28.sp,
                    )
                },
                actions = {
                    OutlinedTopBarIcon(onClick = { onAction(SummaryTransactionUiAction.OnHelpClicked) }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_help),
                            contentDescription = "Help",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(top = 40.dp)
        ) {
            TransactionHeaderSection(
                modifier = Modifier.padding(horizontal = 16.dp),
                iconRes = transaction.iconRes,
                iconBackgroundColor = transaction.iconBackgroundColor,
                title = transaction.title,
                date = transaction.date
            )
            TransactionValueSection(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 40.dp),
                totalAmount = transaction.totalAmount,
                paymentFee = transaction.paymentFee,
            )
            Spacer(modifier = Modifier.weight(1f))
            CardBottomSheet(
                cards = cards,
                onProceedClicked = { onAction(SummaryTransactionUiAction.OnProceedClicked) }
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun SummaryTransactionScreenPreview() {
    WPayTheme {
        SummaryTransactionScreenContent(
            modifier = Modifier.background(primaryLight),
            onAction = {},
            transaction = transaction,
            cards = listOf(DummyValues.card)
        )
    }
}