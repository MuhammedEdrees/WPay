package org.codeslu.wpay

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.codeslu.wpay.app.navigation.Route
import org.codeslu.wpay.ui.components.BackgroundContainer
import org.codeslu.wpay.ui.components.BottomNavigationBar
import org.codeslu.wpay.ui.home.HomeScreen
import org.codeslu.wpay.ui.notifications.NotificationsScreen
import org.codeslu.wpay.ui.permission.PermissionNotGrantedScreen
import org.codeslu.wpay.ui.scantopay.ScanToPayScreen
import org.codeslu.wpay.ui.statistics.StatisticsScreen
import org.codeslu.wpay.ui.summarytransaction.SummaryTransactionScreen
import org.codeslu.wpay.ui.theme.WPayTheme

class MainActivity : ComponentActivity() {
    private val cameraPermissionRequest =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                displayApp()
            } else {
                displayPermissionNotGrantedScreen()
            }

        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        when (PackageManager.PERMISSION_GRANTED) {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) -> {
                displayApp()
            }

            else -> {
                cameraPermissionRequest.launch(Manifest.permission.CAMERA)
            }
        }
    }

    private fun displayApp() {
        enableEdgeToEdge()
        setContent {
            WPayTheme {
                App()
            }
        }
    }

    private fun displayPermissionNotGrantedScreen() {
        setContent {
            WPayTheme {
                PermissionNotGrantedScreen(
                    onRequestPermission = {
                        cameraPermissionRequest.launch(Manifest.permission.CAMERA)
                    }
                )
            }
        }
    }
}

@Composable
fun App() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = (navBackStackEntry?.destination?.route
        ?: Route.HomeScreenRoute::class.qualifiedName.orEmpty()).substringBefore("?")
    var isBottomBarVisible by remember {
        mutableStateOf(true)
    }
    BackgroundContainer(
        modifier = Modifier.fillMaxSize(),
        currentRoute = currentRoute
    ) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding(),
            containerColor = Color.Transparent,
            bottomBar = {
                BottomNavigationBar(
                    isBottomBarVisible = isBottomBarVisible,
                    currentRoute = currentRoute,
                    onNavigate = { route ->
                        navController.navigate(route)
                    }
                )
            },
        ) { paddingValues ->
            NavigationHost(
                modifier = Modifier.padding(paddingValues),
                navController = navController,
                onChangeBottomBarVisibility = {
                    isBottomBarVisible = it
                }
            )
        }
    }
}


@Composable
private fun NavigationHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    onChangeBottomBarVisibility: (Boolean) -> Unit
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Route.HomeScreenRoute
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
                onProceedClicked = { navController.navigate(Route.PaymentReceiptScreenRoute) }
            )
        }
        composable<Route.ConfirmPasswordScreenRoute> {
            onChangeBottomBarVisibility(false)
        }
        composable<Route.PaymentReceiptScreenRoute> {
            onChangeBottomBarVisibility(false)
        }
    }
}
