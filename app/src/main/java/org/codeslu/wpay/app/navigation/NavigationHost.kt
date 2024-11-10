package org.codeslu.wpay.app.navigation

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.codeslu.wpay.ui.confirmpassword.ConfirmPasswordScreen
import org.codeslu.wpay.ui.home.HomeScreen
import org.codeslu.wpay.ui.notifications.NotificationsScreen
import org.codeslu.wpay.ui.paymentreceipt.PaymentReceiptScreen
import org.codeslu.wpay.ui.scantopay.ScanToPayScreen
import org.codeslu.wpay.ui.statistics.StatisticsScreen
import org.codeslu.wpay.ui.summarytransaction.SummaryTransactionScreen

@Composable
fun NavigationHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    onChangeBottomBarVisibility: (Boolean) -> Unit
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Route.HomeScreenRoute,
        enterTransition = {
            fadeIn(
                animationSpec = tween(
                    500, easing = LinearEasing
                )
            )
        },
        exitTransition = {
            fadeOut(
                animationSpec = tween(
                    500, easing = LinearEasing
                )
            )
        }
    ) {
        composable<Route.HomeScreenRoute> {
            onChangeBottomBarVisibility(true)
            HomeScreen()
        }
        composable<Route.NotificationsScreenRoute> {
            onChangeBottomBarVisibility(false)
            NotificationsScreen(
                onBackClicked = { navController.popBackStack() }
            )
        }
        composable<Route.StatisticsScreenRoute> {
            onChangeBottomBarVisibility(true)
            StatisticsScreen(
                onBackClicked = { navController.popBackStack() }
            )
        }
        composable<Route.ProfileScreenRoute> {
            onChangeBottomBarVisibility(true)
        }
        composable<Route.ScanToPayScreenRoute> {
            onChangeBottomBarVisibility(true)
            ScanToPayScreen {
                navController.popBackStack()
            }
        }
        composable<Route.SummaryTransactionScreenRoute> {
            onChangeBottomBarVisibility(false)
            SummaryTransactionScreen(
                onBackClicked = { navController.popBackStack() },
                onProceedClicked = { navController.navigate(Route.ConfirmPasswordScreenRoute) }
            )
        }
        composable<Route.ConfirmPasswordScreenRoute> {
            onChangeBottomBarVisibility(false)
            ConfirmPasswordScreen(
                onBackClicked = { navController.popBackStack() },
                onPasswordConfirmed = { navController.navigate(Route.PaymentReceiptScreenRoute) }
            )
        }
        composable<Route.PaymentReceiptScreenRoute> {
            onChangeBottomBarVisibility(false)
            PaymentReceiptScreen(
                onDoneClicked = {
                    navController.navigate(Route.HomeScreenRoute) {
                        popUpTo<Route.PaymentReceiptScreenRoute> {
                            inclusive = true
                        }
                    }
                },
                onPayAgainClicked = {
                    navController.navigate(Route.ScanToPayScreenRoute) {
                        popUpTo<Route.PaymentReceiptScreenRoute> {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}