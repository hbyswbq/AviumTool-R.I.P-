package org.avium.aviumtool

import androidx.compose.foundation.layout.height
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.avium.aviumtool.ui.navigation.AppNavigation
import org.avium.aviumtool.ui.navigation.Screen
import org.avium.aviumtool.ui.navigation.bottomNavItems

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val currentScreen = when (currentDestination?.route) {
        Screen.Home.route -> Screen.Home
        Screen.About.route -> Screen.About
        Screen.SystemUi.route -> Screen.SystemUi
        Screen.Battery.route -> Screen.Battery
        Screen.Package.route -> Screen.Package
        Screen.Input.route -> Screen.Input
        Screen.AliveWP.route -> Screen.AliveWP
        Screen.SysUIEditor.route -> Screen.SysUIEditor
        Screen.FKAD.route -> Screen.FKAD
        else -> Screen.Home // 默认
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = currentScreen.titleResId)) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.onBackground
                )
            )
        },
        bottomBar = {
            NavigationBar(
                modifier = Modifier.height(69.dp),
                containerColor = MaterialTheme.colorScheme.background
            ) {
                bottomNavItems.forEach { item ->
                    NavigationBarItem(
                        selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = item.iconRes),
                                contentDescription = item.label,
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = Color.Transparent,
                            selectedIconColor = MaterialTheme.colorScheme.onBackground,
                            unselectedIconColor = Color.Gray
                        ),
                        label = null
                    )
                }
            }
        }
    ) { innerPadding ->
        AppNavigation(navController = navController, paddingValues = innerPadding)
    }
}