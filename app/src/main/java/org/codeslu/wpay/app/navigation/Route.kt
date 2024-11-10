package org.codeslu.wpay.app.navigation

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object HomeScreenRoute : Route
    @Serializable
    data object NotificationsScreenRoute : Route
    @Serializable
    data object StatisticsScreenRoute : Route
    @Serializable
    data object ProfileScreenRoute : Route
    @Serializable
    data object ScanToPayScreenRoute : Route
    @Serializable
    data object SummaryTransactionScreenRoute : Route
}