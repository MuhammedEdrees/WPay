package org.codeslu.wpay.ui.scantopay

sealed interface ScanToPayUiAction {
    data object OnBackClicked : ScanToPayUiAction
    data object OnHelpClicked : ScanToPayUiAction
}