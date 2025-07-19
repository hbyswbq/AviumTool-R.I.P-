package org.avium.aviumtool.ui.navigation

import android.annotation.DrawableRes
import org.avium.aviumtool.R

// 定义屏幕
sealed class Screen(val route: String, val titleResId: Int) {
    object Home : Screen("home", R.string.home_title)
    object About : Screen("about", R.string.about_title)
    object SystemUi : Screen("system_ui", R.string.system_ui_title)
    object Battery : Screen("battery", R.string.battery_title)
    object Package : Screen("package", R.string.package_title)
    object Input : Screen("input", R.string.input_title)
    object AliveWP : Screen("alivewallpaper", R.string.alivewallpaper_title)
    object SysUIEditor : Screen("sysuieditor", R.string.sysuieditor_title)
    object FKAD : Screen("fkad", R.string.ad_title)
    object DepWallpaper : Screen("depthwallpaper", R.string.depthwallpaper_title)
}

// 导航烂
data class BottomNavItem(
    val label: String,
    @DrawableRes val iconRes: Int,
    val route: String
)

val bottomNavItems = listOf(
    BottomNavItem("Home", R.drawable.icon_home, Screen.Home.route),
    BottomNavItem("About", R.drawable.icon_info, Screen.About.route)
)