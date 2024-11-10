package org.codeslu.wpay.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import org.codeslu.wpay.R
import org.codeslu.wpay.app.navigation.Route
import org.codeslu.wpay.ui.theme.backgroundLight
import org.codeslu.wpay.ui.theme.orange1Light
import org.codeslu.wpay.ui.theme.surfaceLight

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    isBottomBarVisible: Boolean,
    currentRoute: String,
    onNavigate: (Route) -> Unit
) {
    AnimatedVisibility(
        visible = isBottomBarVisible,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it }),
    ) {
        BottomAppBar(
            modifier = modifier
                .height(70.dp),
            containerColor = surfaceLight,
            tonalElevation = 0.dp,
            windowInsets = WindowInsets.ime.only(WindowInsetsSides.Bottom),
            actions = {
                BottomNavigation.entries.forEach { navigationItem ->
                    val isSelected by remember(currentRoute) {
                        derivedStateOf { currentRoute == navigationItem.route::class.qualifiedName }
                    }
                    if(navigationItem == BottomNavigation.Empty){
                        Spacer(
                            modifier = Modifier
                                .width(50.dp)
                                .fillMaxHeight()
                        )
                    } else {
                        NavigationBarItem(
                            colors = NavigationBarItemDefaults.colors(indicatorColor = Color.Transparent),
                            selected = isSelected,
                            alwaysShowLabel = false,
                            icon = {
                                if (isSelected) {
                                    Icon(
                                        painter = painterResource(id = navigationItem.selectedIcon),
                                        contentDescription = null,
                                        tint = Color.Unspecified,
                                        modifier = Modifier.size(24.dp)
                                    )
                                } else {
                                    Icon(
                                        painter = painterResource(id = navigationItem.unselectedIcon),
                                        contentDescription = null,
                                        tint = Color.Unspecified,
                                        modifier = Modifier.size(24.dp)
                                    )
                                }
                            },
                            onClick = {
                                if(!isSelected){
                                    onNavigate(navigationItem.route)
                                }
                            },
                        )
                    }
                }
            }
        )
    }
}

enum class BottomNavigation(
    @DrawableRes val selectedIcon: Int, @DrawableRes val unselectedIcon: Int, val route: Route
) {
    Home(
        selectedIcon = R.drawable.ic_home_filled,
        unselectedIcon = R.drawable.ic_home_outlined,
        route = Route.HomeScreenRoute
    ),
    Statistics(
        selectedIcon = R.drawable.ic_stats_filled,
        unselectedIcon = R.drawable.ic_stats_outlined,
        route = Route.StatisticsScreenRoute
    ),
    Empty(
        selectedIcon = 0,
        unselectedIcon = 0,
        route = Route.EmptyRoute
    ),
    Notifications(
        selectedIcon = R.drawable.ic_notification_filled,
        unselectedIcon = R.drawable.ic_notification_outlined,
        route = Route.NotificationsScreenRoute
    ),
    Profile(
        selectedIcon = R.drawable.ic_profile_filled,
        unselectedIcon = R.drawable.ic_profile_outlined,
        route = Route.ProfileScreenRoute
    );
}