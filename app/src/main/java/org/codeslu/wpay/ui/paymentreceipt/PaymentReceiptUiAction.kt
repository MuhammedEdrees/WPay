package org.codeslu.wpay.ui.paymentreceipt

sealed interface PaymentReceiptUiAction {
    data object OnDoneClicked : PaymentReceiptUiAction
    data object OnPayAgainClicked : PaymentReceiptUiAction
}