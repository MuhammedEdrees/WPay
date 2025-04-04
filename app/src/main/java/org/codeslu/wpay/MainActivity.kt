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
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.codeslu.wpay.app.navigation.NavigationHost
import org.codeslu.wpay.app.navigation.Route
import org.codeslu.wpay.ui.components.BackgroundContainer
import org.codeslu.wpay.ui.components.BottomNavigationBar
import org.codeslu.wpay.ui.permission.PermissionNotGrantedScreen
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
                .imePadding(),
            containerColor = Color.Transparent,
            floatingActionButton = {
                AnimatedVisibility(
                    visible = isBottomBarVisible,
                    enter = slideInVertically(initialOffsetY = { it }),
                    exit = slideOutVertically(targetOffsetY = { it }),
                ) {

                    Box(
                        modifier = Modifier.offset(y = 44.dp)
                    ) {
                        FloatingActionButton(
                            modifier = Modifier.size(56.dp),
                            onClick = {
                                val route =
                                    if (currentRoute == Route.ScanToPayScreenRoute::class.qualifiedName) {
                                        Route.SummaryTransactionScreenRoute
                                    } else {
                                        Route.ScanToPayScreenRoute
                                    }
                                navController.navigate(route)
                            },
                            shape = CircleShape,
                            containerColor = orange1Light
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_scan),
                                contentDescription = null,
                                tint = Color.Unspecified
                            )
                        }
                    }
                }
            },
            floatingActionButtonPosition = FabPosition.Center,
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