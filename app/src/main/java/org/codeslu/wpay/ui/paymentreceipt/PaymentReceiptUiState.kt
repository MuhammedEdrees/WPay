package org.codeslu.wpay.ui.paymentreceipt

import org.codeslu.wpay.data.model.Expense

data class PaymentReceiptUiState(
    val expense: Expense = Expense()
)
