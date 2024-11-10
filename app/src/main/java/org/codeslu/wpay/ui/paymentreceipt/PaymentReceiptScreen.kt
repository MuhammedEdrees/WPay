package org.codeslu.wpay.ui.paymentreceipt

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
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
import org.codeslu.wpay.data.model.Expense
import org.codeslu.wpay.ui.components.TransparentCenterAlignedTopAppBar
import org.codeslu.wpay.ui.confirmpassword.ConfirmPasswordUiAction
import org.codeslu.wpay.ui.paymentreceipt.components.DashedDivider
import org.codeslu.wpay.ui.paymentreceipt.components.PaymentForSection
import org.codeslu.wpay.ui.paymentreceipt.components.PaymentSuccessSection
import org.codeslu.wpay.ui.paymentreceipt.components.SuccessIconSection
import org.codeslu.wpay.ui.paymentreceipt.components.TotalPaymentSection
import org.codeslu.wpay.ui.theme.WPayTheme
import org.codeslu.wpay.ui.theme.greenBackgroundLight
import org.codeslu.wpay.ui.theme.onBackgroundLight
import org.codeslu.wpay.ui.theme.onPrimaryContainerLight
import org.codeslu.wpay.ui.theme.onPrimaryLight
import org.codeslu.wpay.ui.theme.primaryContainerLight
import org.codeslu.wpay.ui.util.DummyValues
import org.koin.androidx.compose.koinViewModel

@Composable
fun PaymentReceiptScreen(
    modifier: Modifier = Modifier,
    viewModel: PaymentReceiptViewModel = koinViewModel(),
    onDoneClicked: () -> Unit,
    onPayAgainClicked: () -> Unit,
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    PaymentReceiptScreenContent(
        modifier = modifier,
        expense = state.expense,
        onAction = { action ->
            when (action) {
                PaymentReceiptUiAction.OnDoneClicked -> onDoneClicked()
                PaymentReceiptUiAction.OnPayAgainClicked -> onPayAgainClicked()
            }
        }
    )
}

@Composable
private fun PaymentReceiptScreenContent(
    modifier: Modifier = Modifier,
    expense: Expense,
    onAction: (PaymentReceiptUiAction) -> Unit,
) {
    Scaffold(
        modifier = modifier.fillMaxSize().padding(top = 16.dp),
        containerColor = Color.Transparent,
        contentColor = onPrimaryLight,
        topBar = {
            TransparentCenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.payment_receipt),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyLarge,
                        fontSize = 20.sp,
                        lineHeight = 28.sp,
                    )
                }
            )
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxWidth(),
            contentColor = onBackgroundLight,
            color = Color.Transparent
        ) {
            Box(
                contentAlignment = Alignment.TopCenter
            ) {
                Image(
                    painter = painterResource(id = R.drawable.bg_receipt),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth()
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    SuccessIconSection()
                    PaymentSuccessSection(
                        modifier = Modifier.padding(top = 24.dp),
                        expenseTitle = expense.title
                    )
                    TotalPaymentSection(
                        modifier = Modifier.padding(top = 16.dp),
                        amount = expense.amount
                    )
                    DashedDivider(
                        modifier = Modifier
                            .padding(top = 16.dp)
                    )
                    PaymentForSection(
                        modifier = Modifier.padding(top = 16.dp),
                        expense = expense
                    )
                    PaymentActionButtonsSection(
                        modifier = Modifier.padding(top = 24.dp),
                        onDoneClicked = { onAction(PaymentReceiptUiAction.OnDoneClicked) },
                        onPayAgainClicked = { onAction(PaymentReceiptUiAction.OnPayAgainClicked) }
                    )
                }

            }
        }
    }
}

@Composable
fun PaymentActionButtonsSection(
    modifier: Modifier = Modifier,
    onDoneClicked: () -> Unit,
    onPayAgainClicked: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            contentPadding = PaddingValues(vertical = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = primaryContainerLight,
                contentColor = onPrimaryLight
            ),
            onClick = { onDoneClicked() }
        ) {
            Text(
                text = stringResource(R.string.done),
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 16.sp,
                lineHeight = 22.sp,
            )
        }
        TextButton(
            modifier = Modifier
                .fillMaxWidth(),
            contentPadding = PaddingValues(vertical = 16.dp),
            colors = ButtonDefaults.textButtonColors(
                contentColor = onPrimaryContainerLight
            ),
            onClick = { onPayAgainClicked() }
        ) {
            Text(
                text = stringResource(R.string.pay_again),
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 16.sp,
                lineHeight = 22.sp,
            )
        }

    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun PaymentReceiptScreenContentPreview() {
    WPayTheme {
        PaymentReceiptScreenContent(
            modifier = Modifier
                .fillMaxSize()
                .background(greenBackgroundLight),
            expense = DummyValues.recentExpenses.first()
        ) {

        }
    }
}