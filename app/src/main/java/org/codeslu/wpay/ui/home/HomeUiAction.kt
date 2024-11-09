package org.codeslu.wpay.ui.home

import androidx.annotation.StringRes

sealed interface HomeUiAction {
    data object OnSettingsClicked: HomeUiAction
    data object OnTransferClicked: HomeUiAction
    data object OnTopUpClicked: HomeUiAction
    data object OnHistoryClicked: HomeUiAction
    data class OnOptionClicked(@StringRes val optionTitleRes: Int): HomeUiAction
    data object OnSeeMoreClicked: HomeUiAction
}