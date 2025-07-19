package org.avium.aviumtool.ui.screens.prop

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.StateFlow
import org.avium.aviumtool.R
import org.avium.aviumtool.data.SettingsRepository
import org.avium.aviumtool.ui.SettingsViewModel
import org.avium.aviumtool.ui.components.SettingsCard
import org.avium.aviumtool.ui.components.TitledCard
import org.avium.aviumtool.ui.screens.systemui.SettingsToggleItem

private data class PropItem(
    val titleResId: Int,
    val propName: String,
    val stateFlow: StateFlow<Boolean>,
    val prefKey: Preferences.Key<Boolean>,
    val isBooleanProp: Boolean = false
)

@Composable
fun PropScreen(viewModel: SettingsViewModel = viewModel()) {
    val propItems = listOf(
        //true or false
        PropItem(R.string.prop_title_alive_wallpaper_conf, "persist.sys.alive.wallpaper.conf", viewModel.aliveWallpaperConf, SettingsRepository.Keys.ALIVE_WALLPAPER_CONF, isBooleanProp = true),
        PropItem(R.string.prop_title_video_wallpaper_conf, "persist.sys.video.wallpaper.conf", viewModel.videoWallpaperConf, SettingsRepository.Keys.VIDEO_WALLPAPER_CONF, isBooleanProp = true),
        PropItem(R.string.prop_title_gamemode_aiuhd_conf, "persist.sys.gamemode.aiuhd.conf", viewModel.gamemodeAiuhdConf, SettingsRepository.Keys.GAMEMODE_AIUHD_CONF, isBooleanProp = true),
        PropItem(R.string.prop_title_smooth_round_corner_conf, "persist.sys.smooth.round.corner.conf", viewModel.smoothRoundCornerConf, SettingsRepository.Keys.SMOOTH_ROUND_CORNER_CONF, isBooleanProp = true),
        PropItem(R.string.prop_title_launcher_blur_conf, "persist.sys.launcher.blur.conf", viewModel.launcherBlurConf, SettingsRepository.Keys.LAUNCHER_BLUR_CONF, isBooleanProp = true),
        PropItem(R.string.prop_title_launcher_recent_blur_conf, "persist.sys.launcher.recent.blur.conf", viewModel.launcherRecentBlurConf, SettingsRepository.Keys.LAUNCHER_RECENT_BLUR_CONF, isBooleanProp = true),
        PropItem(R.string.prop_title_launcher_aisearch_blur_conf, "persist.sys.launcher.aisearch.blur.conf", viewModel.launcherAisearchBlurConf, SettingsRepository.Keys.LAUNCHER_AISEARCH_BLUR_CONF, isBooleanProp = true),
        PropItem(R.string.prop_title_assistant_blur_conf, "persist.sys.assistant.blur.conf", viewModel.assistantBlurConf, SettingsRepository.Keys.ASSISTANT_BLUR_CONF, isBooleanProp = true),
        PropItem(R.string.prop_title_windowmode_blur_conf, "persist.sys.windowmode.blur.conf", viewModel.windowmodeBlurConf, SettingsRepository.Keys.WINDOWMODE_BLUR_CONF, isBooleanProp = true),
        PropItem(R.string.prop_title_gamemode_notification_blur_conf, "persist.sys.gamemode.notification.blur.conf", viewModel.gamemodeNotificationBlurConf, SettingsRepository.Keys.GAMEMODE_NOTIFICATION_BLUR_CONF, isBooleanProp = true),
        PropItem(R.string.prop_title_onehanded_wallpaper_background_blur_conf, "persist.sys.onehanded.wallpaper.background.blur.conf", viewModel.onehandedWallpaperBackgroundBlurConf, SettingsRepository.Keys.ONEHANDED_WALLPAPER_BACKGROUND_BLUR_CONF, isBooleanProp = true),

        //full
        PropItem(R.string.prop_title_systemui_notification_blur_conf, "persist.sys.systemui.notification.blur.conf", viewModel.systemuiNotificationBlurConf, SettingsRepository.Keys.SYSTEMUI_NOTIFICATION_BLUR_CONF),
        PropItem(R.string.prop_title_systemui_panel_blur_conf, "persist.sys.systemui.panel.blur.conf", viewModel.systemuiPanelBlurConf, SettingsRepository.Keys.SYSTEMUI_PANEL_BLUR_CONF),
        PropItem(R.string.prop_title_systemui_control_blur_conf, "persist.sys.systemui.control.blur.conf", viewModel.systemuiControlBlurConf, SettingsRepository.Keys.SYSTEMUI_CONTROL_BLUR_CONF),
        PropItem(R.string.prop_title_systemui_media_wallpaper_conf, "persist.sys.systemui.media.wallpaper.conf", viewModel.systemuiMediaWallpaperConf, SettingsRepository.Keys.SYSTEMUI_MEDIA_WALLPAPER_CONF),
        PropItem(R.string.prop_title_systemui_bouncer_blur_conf, "persist.sys.systemui.bouncer.blur.conf", viewModel.systemuiBouncerBlurConf, SettingsRepository.Keys.SYSTEMUI_BOUNCER_BLUR_CONF),
        PropItem(R.string.prop_title_systemuieditor_blur_conf, "persist.sys.systemuieditor.blur.conf", viewModel.systemuieditorBlurConf, SettingsRepository.Keys.SYSTEMUIEDITOR_BLUR_CONF),
        PropItem(R.string.prop_title_launcher_folder_anim_conf, "persist.sys.launcher.folder.anim.conf", viewModel.launcherFolderAnimConf, SettingsRepository.Keys.LAUNCHER_FOLDER_ANIM_CONF),
        PropItem(R.string.prop_title_launcher_unlockanim_conf, "persist.sys.launcher.unlockanim.conf", viewModel.launcherUnlockanimConf, SettingsRepository.Keys.LAUNCHER_UNLOCKANIM_CONF),
        PropItem(R.string.prop_title_launcher_app_opening_anim_conf, "persist.sys.launcher.app.opening.anim.conf", viewModel.launcherAppOpeningAnimConf, SettingsRepository.Keys.LAUNCHER_APP_OPENING_ANIM_CONF),
        PropItem(R.string.prop_title_launcher_app_closing_anim_conf, "persist.sys.launcher.app.closing.anim.conf", viewModel.launcherAppClosingAnimConf, SettingsRepository.Keys.LAUNCHER_APP_CLOSING_ANIM_CONF),
        PropItem(R.string.prop_title_launcher_capsule_anim_conf, "persist.sys.launcher.capsule.anim.conf", viewModel.launcherCapsuleAnimConf, SettingsRepository.Keys.LAUNCHER_CAPSULE_ANIM_CONF),
        PropItem(R.string.prop_title_launcher_widget_opening_anim_conf, "persist.sys.launcher.widget.opening.anim.conf", viewModel.launcherWidgetOpeningAnimConf, SettingsRepository.Keys.LAUNCHER_WIDGET_OPENING_ANIM_CONF),
        PropItem(R.string.prop_title_windowmode_anim_conf, "persist.sys.windowmode.anim.conf", viewModel.windowmodeAnimConf, SettingsRepository.Keys.WINDOWMODE_ANIM_CONF),
        PropItem(R.string.prop_title_wallpaper_scale_anim_conf, "persist.sys.wallpaper.scale.anim.conf", viewModel.wallpaperScaleAnimConf, SettingsRepository.Keys.WALLPAPER_SCALE_ANIM_CONF),
        PropItem(R.string.prop_title_systemui_control_anim_conf, "persist.sys.systemui.control.anim.conf", viewModel.systemuiControlAnimConf, SettingsRepository.Keys.SYSTEMUI_CONTROL_ANIM_CONF),
        PropItem(R.string.prop_title_systemui_notification_launch_anim_conf, "persist.sys.systemui.notification.launch.anim.conf", viewModel.systemuiNotificationLaunchAnimConf, SettingsRepository.Keys.SYSTEMUI_NOTIFICATION_LAUNCH_ANIM_CONF),
        PropItem(R.string.prop_title_systemui_screenshot_anim_conf, "persist.sys.systemui.screenshot.anim.conf", viewModel.systemuiScreenshotAnimConf, SettingsRepository.Keys.SYSTEMUI_SCREENSHOT_ANIM_CONF),
        PropItem(R.string.prop_title_systemui_wakeup_anim_conf, "persist.sys.systemui.wakeup.anim.conf", viewModel.systemuiWakeupAnimConf, SettingsRepository.Keys.SYSTEMUI_WAKEUP_ANIM_CONF)
    )
    val gameUnlockFps by viewModel.gameUnlockFps.collectAsStateWithLifecycle()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(vertical = 16.dp)
    ) {
        item {
            SettingsCard {
                PropHeaderSection()
            }
        }
//        item {
//            TitledCard(title = stringResource(id = R.string.game_unlockfps_prop)) {
//                SettingsToggleItem(
//                    title = stringResource(id = R.string.prop_game_unlock_fps_switch),
//                    checked = gameUnlockFps,
//                    onCheckedChange = { enabled ->
//                        viewModel.setGameUnlockFpsEnabled(enabled) }
//                )
//            }
//        }
        // Prop Blur
        item {
            TitledCard(title = stringResource(id = R.string.prop_item_title)) {
                propItems.forEachIndexed { index, item ->
                    val checked by item.stateFlow.collectAsStateWithLifecycle()

                    SettingsToggleItem(
                        title = stringResource(id = item.titleResId),
                        checked = checked,
                        onCheckedChange = { enabled ->
                            viewModel.setPropEnabled(item.prefKey, item.propName, enabled, item.isBooleanProp)
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun PropHeaderSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_home_prop),
            contentDescription = null,
            modifier = Modifier.size(60.dp),
            tint = Color.Unspecified
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.prop_title),
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = stringResource(id = R.string.system_ui_warning_subtitle),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )
    }
}