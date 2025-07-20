package org.avium.aviumtool.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.avium.aviumtool.R
import org.avium.aviumtool.ui.components.SettingsCard
import org.avium.aviumtool.ui.components.SettingsRowItem
import org.avium.aviumtool.ui.navigation.Screen

@Composable
fun HomeScreen(navController: NavController) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(vertical = 16.dp)
    ) {
        // 头图
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(horizontal = 16.dp),
                shape = MaterialTheme.shapes.small
            ) {
                Image(
                    painter = painterResource(id = R.drawable.home_page),
                    contentDescription = "Home",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        // 功能列
        item {
            SettingsCard {
                // 系统界面
                SettingsRowItem(
                    icon = painterResource(id = R.drawable.ic_home_android),
                    title = stringResource(id = R.string.home_item_system_ui_title),
                    subtitle = stringResource(id = R.string.home_item_system_ui_subtitle),
                    onClick = { navController.navigate(Screen.SystemUi.route) }
                )

//                // 分割线
//                Divider(
//                    modifier = Modifier.padding(horizontal = 16.dp),
//                    color = MaterialTheme.colorScheme.background
//                )

                // 电池
                SettingsRowItem(
                    icon = painterResource(id = R.drawable.ic_home_battery),
                    title = stringResource(id = R.string.home_item_battery_title),
                    subtitle = stringResource(id = R.string.home_item_battery_subtitle),
                    onClick = { navController.navigate(Screen.Battery.route) }
                )

                // 系统桌面
                SettingsRowItem(
                    icon = painterResource(id = R.drawable.ic_home_launcher),
                    title = stringResource(id = R.string.home_item_launcher_title),
                    subtitle = stringResource(id = R.string.home_item_laucnher_subtitle),
                    onClick = { navController.navigate(Screen.Launcher.route) }
                )


                // 安装包
                SettingsRowItem(
                    icon = painterResource(id = R.drawable.ic_home_package),
                    title = stringResource(id = R.string.home_item_package_title),
                    subtitle = stringResource(id = R.string.home_item_package_subtitle),
                    onClick = { navController.navigate(Screen.Package.route) }
                )


                // 输入法
                SettingsRowItem(
                    icon = painterResource(id = R.drawable.ic_home_keyboard),
                    title = stringResource(id = R.string.home_item_input_title),
                    subtitle = stringResource(id = R.string.home_item_input_subtitle),
                    onClick = { navController.navigate(Screen.Input.route) }
                )

                //Alive 壁纸
                SettingsRowItem(
                    icon = painterResource(id = R.drawable.ic_home_wallpaper),
                    title = stringResource(id = R.string.home_item_alivewallpaper_title),
                    subtitle = stringResource(id = R.string.home_item_alivewallapaper_subtitle),
                    onClick = { navController.navigate(Screen.AliveWP.route) }
                )


                //外观编辑器
                SettingsRowItem(
                    icon = painterResource(id = R.drawable.ic_home_editor),
                    title = stringResource(id = R.string.home_item_sysuieditor_title),
                    subtitle = stringResource(id = R.string.home_item_sysuieditor_subtitle),
                    onClick = { navController.navigate(Screen.SysUIEditor.route) }
                )


                //fk flyme ad
                SettingsRowItem(
                    icon = painterResource(id = R.drawable.icon_ad),
                    title = stringResource(id = R.string.home_item_fkad_title),
                    subtitle = stringResource(id = R.string.home_item_ad_subtitle),
                    onClick = { navController.navigate(Screen.FKAD.route) }
                )

                //景深壁纸
                SettingsRowItem(
                    icon = painterResource(id = R.drawable.ic_home_depthwallpaper),
                    title = stringResource(id = R.string.home_item_depthwallpaper_title),
                    subtitle = stringResource(id = R.string.home_item_depthwallpaper_subtitle),
                    onClick = { navController.navigate(Screen.DepWallpaper.route) }
                )

                // Prop
                SettingsRowItem(
                    icon = painterResource(id = R.drawable.ic_home_prop),
                    title = stringResource(id = R.string.home_item_prop_title),
                    subtitle = stringResource(id = R.string.home_item_prop_subtitle),
                    onClick = { navController.navigate(Screen.Prop.route) }
                )
            }
        }
    }
}