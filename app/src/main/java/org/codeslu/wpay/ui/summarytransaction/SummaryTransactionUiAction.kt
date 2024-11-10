package org.codeslu.wpay.ui.summarytransaction

sealed interface SummaryTransactionUiAction {
    data object OnBackClicked : SummaryTransactionUiAction
    data object OnHelpClicked : SummaryTransactionUiAction
    data object OnProceedClicked : SummaryTransactionUiAction
}