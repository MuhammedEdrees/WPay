package org.codeslu.wpay.ui.notifications

sealed interface NotificationsUiAction {
    data object OnBackClicked : NotificationsUiAction
    data object OnSettingsClicked : NotificationsUiAction
    data object OnMarkAsReadClicked : NotificationsUiAction
    data class OnNotificationActionClicked(val actionId: String) : NotificationsUiAction
}