package org.codeslu.wpay

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.codeslu.wpay.app.navigation.BottomNavigationBar
import org.codeslu.wpay.app.navigation.Route
import org.codeslu.wpay.ui.home.HomeScreen
import org.codeslu.wpay.ui.notifications.NotificationsScreen
import org.codeslu.wpay.ui.permission.PermissionNotGrantedScreen
import org.codeslu.wpay.ui.scantopay.ScanToPayScreen
import org.codeslu.wpay.ui.statistics.StatisticsScreen
import org.codeslu.wpay.ui.theme.WPayTheme
import org.codeslu.wpay.ui.theme.orange1Light

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
    private fun displayApp(){
        enableEdgeToEdge()
        setContent {
            WPayTheme {
                App()
            }
        }
    }
    private fun displayPermissionNotGrantedScreen(){
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
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding(),
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
        NavHost(
            modifier = Modifier.padding(paddingValues),
            navController = navController,
            startDestination = Route.HomeScreenRoute
        ) {
            composable<Route.HomeScreenRoute> {
                isBottomBarVisible = true
                HomeScreen()
            }
            composable<Route.NotificationsScreenRoute> {
                isBottomBarVisible = false
                NotificationsScreen(
                    onBackClicked = { navController.popBackStack() }
                )
            }
            composable<Route.StatisticsScreenRoute> {
                isBottomBarVisible = true
                StatisticsScreen(
                    onBackClicked = { navController.popBackStack() }
                )
            }
            composable<Route.ProfileScreenRoute> {
                isBottomBarVisible = true
            }
            composable<Route.ScanToPayScreenRoute> {
                isBottomBarVisible = true
                ScanToPayScreen {
                    navController.popBackStack()
                }
            }
            composable<Route.SummaryTransactionScreenRoute> {
                isBottomBarVisible = false
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Summary Transaction Screen", style = MaterialTheme.typography.headlineMedium)
                }
            }
        }
    }
}
