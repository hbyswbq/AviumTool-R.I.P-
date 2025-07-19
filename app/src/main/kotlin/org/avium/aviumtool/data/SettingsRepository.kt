package org.avium.aviumtool.data

import android.R
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SettingsRepository(private val context: Context) {

    // Key
    object Keys {
        val MODERN_CLOCK_ENABLED = booleanPreferencesKey("modern_clock_enabled")
        val BATTERY_LIFEPAN_ENABLED = booleanPreferencesKey("battery_lifepan_enabled")
        val PACKAGE_ENABLE = booleanPreferencesKey("package_skip_check_enable")
        val INPUT_METHOD_5K = booleanPreferencesKey("input_5k_Enabled")
        val INPUT_METHOD_150 = booleanPreferencesKey("input_150_Enabled")
        val GRGLASS = booleanPreferencesKey("gr_glass")
        val WIDGET_ENABLE = booleanPreferencesKey("widget_enabled")
        val AOD_ENABLE = booleanPreferencesKey("aod_enbled")
        val AD_WEATHER = booleanPreferencesKey("ad_weather")
        val AD_DAY = booleanPreferencesKey("ad_day")
        val ALIVE_WALLPAPER_CONF = booleanPreferencesKey("persist.sys.alive.wallpaper.conf")
        val VIDEO_WALLPAPER_CONF = booleanPreferencesKey("persist.sys.video.wallpaper.conf")
        val GAMEMODE_AIUHD_CONF = booleanPreferencesKey("persist.sys.gamemode.aiuhd.conf")
        val SMOOTH_ROUND_CORNER_CONF = booleanPreferencesKey("persist.sys.smooth.round.corner.conf")
        val LAUNCHER_BLUR_CONF = booleanPreferencesKey("persist.sys.launcher.blur.conf")
        val LAUNCHER_RECENT_BLUR_CONF = booleanPreferencesKey("persist.sys.launcher.recent.blur.conf")
        val LAUNCHER_AISEARCH_BLUR_CONF = booleanPreferencesKey("persist.sys.launcher.aisearch.blur.conf")
        val ASSISTANT_BLUR_CONF = booleanPreferencesKey("persist.sys.assistant.blur.conf")
        val SYSTEMUI_NOTIFICATION_BLUR_CONF = booleanPreferencesKey("persist.sys.systemui.notification.blur.conf")
        val SYSTEMUI_PANEL_BLUR_CONF = booleanPreferencesKey("persist.sys.systemui.panel.blur.conf")
        val SYSTEMUI_CONTROL_BLUR_CONF = booleanPreferencesKey("persist.sys.systemui.control.blur.conf")
        val SYSTEMUI_MEDIA_WALLPAPER_CONF = booleanPreferencesKey("persist.sys.systemui.media.wallpaper.conf")
        val SYSTEMUI_BOUNCER_BLUR_CONF = booleanPreferencesKey("persist.sys.systemui.bouncer.blur.conf")
        val SYSTEMUIEDITOR_BLUR_CONF = booleanPreferencesKey("persist.sys.systemuieditor.blur.conf")
        val WINDOWMODE_BLUR_CONF = booleanPreferencesKey("persist.sys.windowmode.blur.conf")
        val GAMEMODE_NOTIFICATION_BLUR_CONF = booleanPreferencesKey("persist.sys.gamemode.notification.blur.conf")
        val ONEHANDED_WALLPAPER_BACKGROUND_BLUR_CONF = booleanPreferencesKey("persist.sys.onehanded.wallpaper.background.blur.conf")
        val LAUNCHER_FOLDER_ANIM_CONF = booleanPreferencesKey("persist.sys.launcher.folder.anim.conf")
        val LAUNCHER_UNLOCKANIM_CONF = booleanPreferencesKey("persist.sys.launcher.unlockanim.conf")
        val LAUNCHER_APP_OPENING_ANIM_CONF = booleanPreferencesKey("persist.sys.launcher.app.opening.anim.conf")
        val LAUNCHER_APP_CLOSING_ANIM_CONF = booleanPreferencesKey("persist.sys.launcher.app.closing.anim.conf")
        val LAUNCHER_CAPSULE_ANIM_CONF = booleanPreferencesKey("persist.sys.launcher.capsule.anim.conf")
        val LAUNCHER_WIDGET_OPENING_ANIM_CONF = booleanPreferencesKey("persist.sys.launcher.widget.opening.anim.conf")
        val WINDOWMODE_ANIM_CONF = booleanPreferencesKey("persist.sys.windowmode.anim.conf")
        val WALLPAPER_SCALE_ANIM_CONF = booleanPreferencesKey("persist.sys.wallpaper.scale.anim.conf")
        val SYSTEMUI_CONTROL_ANIM_CONF = booleanPreferencesKey("persist.sys.systemui.control.anim.conf")
        val SYSTEMUI_NOTIFICATION_LAUNCH_ANIM_CONF = booleanPreferencesKey("persist.sys.systemui.notification.launch.anim.conf")
        val SYSTEMUI_SCREENSHOT_ANIM_CONF = booleanPreferencesKey("persist.sys.systemui.screenshot.anim.conf")
        val SYSTEMUI_WAKEUP_ANIM_CONF = booleanPreferencesKey("persist.sys.systemui.wakeup.anim.conf")
        val GAME_UNLOCK_FPS = booleanPreferencesKey("ro.surface_flinger.game_default_frame_rate_override")

    }

    // 读
    fun getBooleanFlow(key: Preferences.Key<Boolean>, defaultValue: Boolean): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            preferences[key] ?: defaultValue
        }
    }

    //写入
    suspend fun setBoolean(key: Preferences.Key<Boolean>, value: Boolean) {
        context.dataStore.edit { settings ->
            settings[key] = value
        }
    }

    val modernClockEnabledFlow: Flow<Boolean> = getBooleanFlow(Keys.MODERN_CLOCK_ENABLED, false)
    val batteryLifePanEnabledFlow: Flow<Boolean> = getBooleanFlow(Keys.BATTERY_LIFEPAN_ENABLED, false)
    val packageEnabledFlow: Flow<Boolean> = getBooleanFlow(Keys.PACKAGE_ENABLE, false)
    val inputMethod5kFlow: Flow<Boolean> = getBooleanFlow(Keys.INPUT_METHOD_5K, false)
    val inputMethod150Flow: Flow<Boolean> = getBooleanFlow(Keys.INPUT_METHOD_150, false)
    val aWGlassFlow: Flow<Boolean> = getBooleanFlow(Keys.GRGLASS, false)
    val sysuiWidgetEditorFlow: Flow<Boolean> = getBooleanFlow(Keys.WIDGET_ENABLE, false)
    val sysuiAodEditorFlow: Flow<Boolean> = getBooleanFlow(Keys.AOD_ENABLE, false)
    val adWeatherFlow: Flow<Boolean> = getBooleanFlow(Keys.AD_WEATHER, false)
    val adDayFlow: Flow<Boolean> = getBooleanFlow(Keys.AD_DAY, false)
//Prop
    val aliveWallpaperConfFlow: Flow<Boolean> = getBooleanFlow(Keys.ALIVE_WALLPAPER_CONF, false)
    val videoWallpaperConfFlow: Flow<Boolean> = getBooleanFlow(Keys.VIDEO_WALLPAPER_CONF, false)
    val gamemodeAiuhdConfFlow: Flow<Boolean> = getBooleanFlow(Keys.GAMEMODE_AIUHD_CONF, false)
    val smoothRoundCornerConfFlow: Flow<Boolean> = getBooleanFlow(Keys.SMOOTH_ROUND_CORNER_CONF, false)
    val launcherBlurConfFlow: Flow<Boolean> = getBooleanFlow(Keys.LAUNCHER_BLUR_CONF, false)
    val launcherRecentBlurConfFlow: Flow<Boolean> = getBooleanFlow(Keys.LAUNCHER_RECENT_BLUR_CONF, false)
    val launcherAisearchBlurConfFlow: Flow<Boolean> = getBooleanFlow(Keys.LAUNCHER_AISEARCH_BLUR_CONF, false)
    val assistantBlurConfFlow: Flow<Boolean> = getBooleanFlow(Keys.ASSISTANT_BLUR_CONF, false)
    val systemuiNotificationBlurConfFlow: Flow<Boolean> = getBooleanFlow(Keys.SYSTEMUI_NOTIFICATION_BLUR_CONF, false)
    val systemuiPanelBlurConfFlow: Flow<Boolean> = getBooleanFlow(Keys.SYSTEMUI_PANEL_BLUR_CONF, false)
    val systemuiControlBlurConfFlow: Flow<Boolean> = getBooleanFlow(Keys.SYSTEMUI_CONTROL_BLUR_CONF, false)
    val systemuiMediaWallpaperConfFlow: Flow<Boolean> = getBooleanFlow(Keys.SYSTEMUI_MEDIA_WALLPAPER_CONF, false)
    val systemuiBouncerBlurConfFlow: Flow<Boolean> = getBooleanFlow(Keys.SYSTEMUI_BOUNCER_BLUR_CONF, false)
    val systemuieditorBlurConfFlow: Flow<Boolean> = getBooleanFlow(Keys.SYSTEMUIEDITOR_BLUR_CONF, false)
    val windowmodeBlurConfFlow: Flow<Boolean> = getBooleanFlow(Keys.WINDOWMODE_BLUR_CONF, false)
    val gamemodeNotificationBlurConfFlow: Flow<Boolean> = getBooleanFlow(Keys.GAMEMODE_NOTIFICATION_BLUR_CONF, false)
    val onehandedWallpaperBackgroundBlurConfFlow: Flow<Boolean> = getBooleanFlow(Keys.ONEHANDED_WALLPAPER_BACKGROUND_BLUR_CONF, false)
    val launcherFolderAnimConfFlow: Flow<Boolean> = getBooleanFlow(Keys.LAUNCHER_FOLDER_ANIM_CONF, false)
    val launcherUnlockanimConfFlow: Flow<Boolean> = getBooleanFlow(Keys.LAUNCHER_UNLOCKANIM_CONF, false)
    val launcherAppOpeningAnimConfFlow: Flow<Boolean> = getBooleanFlow(Keys.LAUNCHER_APP_OPENING_ANIM_CONF, false)
    val launcherAppClosingAnimConfFlow: Flow<Boolean> = getBooleanFlow(Keys.LAUNCHER_APP_CLOSING_ANIM_CONF, false)
    val launcherCapsuleAnimConfFlow: Flow<Boolean> = getBooleanFlow(Keys.LAUNCHER_CAPSULE_ANIM_CONF, false)
    val launcherWidgetOpeningAnimConfFlow: Flow<Boolean> = getBooleanFlow(Keys.LAUNCHER_WIDGET_OPENING_ANIM_CONF, false)
    val windowmodeAnimConfFlow: Flow<Boolean> = getBooleanFlow(Keys.WINDOWMODE_ANIM_CONF, false)
    val wallpaperScaleAnimConfFlow: Flow<Boolean> = getBooleanFlow(Keys.WALLPAPER_SCALE_ANIM_CONF, false)
    val systemuiControlAnimConfFlow: Flow<Boolean> = getBooleanFlow(Keys.SYSTEMUI_CONTROL_ANIM_CONF, false)
    val systemuiNotificationLaunchAnimConfFlow: Flow<Boolean> = getBooleanFlow(Keys.SYSTEMUI_NOTIFICATION_LAUNCH_ANIM_CONF, false)
    val systemuiScreenshotAnimConfFlow: Flow<Boolean> = getBooleanFlow(Keys.SYSTEMUI_SCREENSHOT_ANIM_CONF, false)
    val systemuiWakeupAnimConfFlow: Flow<Boolean> = getBooleanFlow(Keys.SYSTEMUI_WAKEUP_ANIM_CONF, false)
    val gameUnlockFpsFlow : Flow<Boolean> = getBooleanFlow(Keys.GAME_UNLOCK_FPS, false)


}