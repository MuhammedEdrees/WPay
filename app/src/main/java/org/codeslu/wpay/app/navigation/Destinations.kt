package org.codeslu.wpay.app.navigation

import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer

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
}