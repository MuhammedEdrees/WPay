package org.codeslu.wpay.ui.confirmpassword

sealed interface ConfirmPasswordUiAction {
    data object OnBackClicked : ConfirmPasswordUiAction
    data class OnConfirmPasswordClicked(val password: String) : ConfirmPasswordUiAction
}