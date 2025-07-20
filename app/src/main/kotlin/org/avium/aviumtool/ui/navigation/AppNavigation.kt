package org.avium.aviumtool.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.avium.aviumtool.ui.screens.about.AboutScreen
import org.avium.aviumtool.ui.screens.ad.FKADScreen
import org.avium.aviumtool.ui.screens.alivewallpaper.AWallpaperScreen
import org.avium.aviumtool.ui.screens.battery.BatteryScreen
import org.avium.aviumtool.ui.screens.battery.LauncherScreen
import org.avium.aviumtool.ui.screens.depthwallpaper.DepWallpaperScreen
import org.avium.aviumtool.ui.screens.home.HomeScreen
import org.avium.aviumtool.ui.screens.inputmethod.InputScreen
import org.avium.aviumtool.ui.screens.packages.PackagesScreen
import org.avium.aviumtool.ui.screens.prop.PropScreen
import org.avium.aviumtool.ui.screens.systemui.SystemUiScreen
import org.avium.aviumtool.ui.screens.systemuieditor.SysyUIEditorScreen

@Composable
fun AppNavigation(navController: NavHostController, paddingValues: PaddingValues, snackbarHostState: SnackbarHostState) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = Modifier.padding(paddingValues)
    ) {
        composable(Screen.Home.route) {
            HomeScreen(navController)
        }
        composable(Screen.About.route) {
            AboutScreen()
        }
        composable(Screen.SystemUi.route) {
            SystemUiScreen()
        }
        composable(Screen.Battery.route) {
            BatteryScreen()
        }
        composable(Screen.Package.route) {
            PackagesScreen()
        }
        composable(Screen.Input.route) {
            InputScreen()
        }
        composable(Screen.AliveWP.route) {
            AWallpaperScreen()
        }
        composable(Screen.SysUIEditor.route) {
            SysyUIEditorScreen()
        }
        composable(Screen.FKAD.route) {
            FKADScreen()
        }
        composable(Screen.DepWallpaper.route) {
            DepWallpaperScreen(snackbarHostState = snackbarHostState)
        }
        composable(Screen.Prop.route) {
            PropScreen()
        }
        composable(Screen.Launcher.route) {
            LauncherScreen()
        }
    }
}