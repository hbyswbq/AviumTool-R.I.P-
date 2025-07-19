package org.avium.aviumtool.ui

import android.app.Application
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.avium.aviumtool.data.SettingsRepository
import org.avium.aviumtool.ui.screens.prop.PropUtils

class SettingsViewModel(application: Application) : AndroidViewModel(application) {

    private val settingsRepository = SettingsRepository(application)

    // SystemUI
    val modernClockEnabled: StateFlow<Boolean> = settingsRepository.modernClockEnabledFlow
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), false)

    // Battery
    val batteryLifePanEnabled: StateFlow<Boolean> = settingsRepository.batteryLifePanEnabledFlow
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), false)

    // Package
    val packageEnabled: StateFlow<Boolean> = settingsRepository.packageEnabledFlow
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), false)

    // Input
    val inputMethod5k: StateFlow<Boolean> = settingsRepository.inputMethod5kFlow
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), false)

    val inputMethod150: StateFlow<Boolean> = settingsRepository.inputMethod150Flow
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), false)

    // alivewallpaper
    val aWallpaperEnable: StateFlow<Boolean> = settingsRepository.aWGlassFlow
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), false)

    // systemuieditor
    val sysuiWidgetEnable: StateFlow<Boolean> = settingsRepository.sysuiWidgetEditorFlow
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), false)

    val sysuiAodEnable: StateFlow<Boolean> = settingsRepository.sysuiAodEditorFlow
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), false)

    //AD
    val adWeatherEnable: StateFlow<Boolean> = settingsRepository.adWeatherFlow
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), false)

    val adDayEnable: StateFlow<Boolean> = settingsRepository.adDayFlow
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), false)

    // Prop
    private fun getPropStateFlow(flow: Flow<Boolean>): StateFlow<Boolean> {
        return flow.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), false)
    }

    val aliveWallpaperConf = getPropStateFlow(settingsRepository.aliveWallpaperConfFlow)
    val videoWallpaperConf = getPropStateFlow(settingsRepository.videoWallpaperConfFlow)
    val gamemodeAiuhdConf = getPropStateFlow(settingsRepository.gamemodeAiuhdConfFlow)
    val smoothRoundCornerConf = getPropStateFlow(settingsRepository.smoothRoundCornerConfFlow)
    val launcherBlurConf = getPropStateFlow(settingsRepository.launcherBlurConfFlow)
    val launcherRecentBlurConf = getPropStateFlow(settingsRepository.launcherRecentBlurConfFlow)
    val launcherAisearchBlurConf = getPropStateFlow(settingsRepository.launcherAisearchBlurConfFlow)
    val assistantBlurConf = getPropStateFlow(settingsRepository.assistantBlurConfFlow)
    val systemuiNotificationBlurConf = getPropStateFlow(settingsRepository.systemuiNotificationBlurConfFlow)
    val systemuiPanelBlurConf = getPropStateFlow(settingsRepository.systemuiPanelBlurConfFlow)
    val systemuiControlBlurConf = getPropStateFlow(settingsRepository.systemuiControlBlurConfFlow)
    val systemuiMediaWallpaperConf = getPropStateFlow(settingsRepository.systemuiMediaWallpaperConfFlow)
    val systemuiBouncerBlurConf = getPropStateFlow(settingsRepository.systemuiBouncerBlurConfFlow)
    val systemuieditorBlurConf = getPropStateFlow(settingsRepository.systemuieditorBlurConfFlow)
    val windowmodeBlurConf = getPropStateFlow(settingsRepository.windowmodeBlurConfFlow)
    val gamemodeNotificationBlurConf = getPropStateFlow(settingsRepository.gamemodeNotificationBlurConfFlow)
    val onehandedWallpaperBackgroundBlurConf = getPropStateFlow(settingsRepository.onehandedWallpaperBackgroundBlurConfFlow)
    val launcherFolderAnimConf = getPropStateFlow(settingsRepository.launcherFolderAnimConfFlow)
    val launcherUnlockanimConf = getPropStateFlow(settingsRepository.launcherUnlockanimConfFlow)
    val launcherAppOpeningAnimConf = getPropStateFlow(settingsRepository.launcherAppOpeningAnimConfFlow)
    val launcherAppClosingAnimConf = getPropStateFlow(settingsRepository.launcherAppClosingAnimConfFlow)
    val launcherCapsuleAnimConf = getPropStateFlow(settingsRepository.launcherCapsuleAnimConfFlow)
    val launcherWidgetOpeningAnimConf = getPropStateFlow(settingsRepository.launcherWidgetOpeningAnimConfFlow)
    val windowmodeAnimConf = getPropStateFlow(settingsRepository.windowmodeAnimConfFlow)
    val wallpaperScaleAnimConf = getPropStateFlow(settingsRepository.wallpaperScaleAnimConfFlow)
    val systemuiControlAnimConf = getPropStateFlow(settingsRepository.systemuiControlAnimConfFlow)
    val systemuiNotificationLaunchAnimConf = getPropStateFlow(settingsRepository.systemuiNotificationLaunchAnimConfFlow)
    val systemuiScreenshotAnimConf = getPropStateFlow(settingsRepository.systemuiScreenshotAnimConfFlow)
    val systemuiWakeupAnimConf = getPropStateFlow(settingsRepository.systemuiWakeupAnimConfFlow)

    val gameUnlockFps: StateFlow<Boolean> = settingsRepository.gameUnlockFpsFlow
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), false)


    fun updateBooleanSetting(key: Preferences.Key<Boolean>, value: Boolean) {
        viewModelScope.launch {
            settingsRepository.setBoolean(key, value)
        }
    }

    fun setPropEnabled(key: Preferences.Key<Boolean>, propName: String, enabled: Boolean, isBooleanProp: Boolean = false) {
        updateBooleanSetting(key, enabled)
        viewModelScope.launch {
            if (isBooleanProp) {
                PropUtils.setBooleanProp(propName, enabled)
            } else {
                PropUtils.setProp(propName, enabled)
            }
        }
    }

    //Game
    fun setGameUnlockFpsEnabled(enabled: Boolean) {
        if (enabled) {
            PropUtils.setGameFrameRateOverride(120)
        } else {
            PropUtils.setGameFrameRateOverride(60)
        }
        updateBooleanSetting(SettingsRepository.Keys.GAME_UNLOCK_FPS, enabled)
    }

    // SystemUI
    fun setModernClockEnabled(enabled: Boolean) {
        updateBooleanSetting(SettingsRepository.Keys.MODERN_CLOCK_ENABLED, enabled)
    }

    // Battery
    fun setBatterySwitch1Enabled(enabled: Boolean) {
        updateBooleanSetting(SettingsRepository.Keys.BATTERY_LIFEPAN_ENABLED, enabled)
    }

    // Package
    fun setPackageEnabled(enabled: Boolean) {
        updateBooleanSetting(SettingsRepository.Keys.PACKAGE_ENABLE, enabled)
    }

    // Input
    fun setInput5kEnabled(enabled: Boolean) {
        updateBooleanSetting(SettingsRepository.Keys.INPUT_METHOD_5K, enabled)
    }

    fun setInput150Enabled(enabled: Boolean) {
        updateBooleanSetting(SettingsRepository.Keys.INPUT_METHOD_150, enabled)
    }

    // alivewallpaper
    fun setAWallpaperEnabled(enabled: Boolean) {
        updateBooleanSetting(SettingsRepository.Keys.GRGLASS, enabled)
    }

    // systemuieditor
    fun setSysUIWidgetEditorEnabled(enabled: Boolean) {
        updateBooleanSetting(SettingsRepository.Keys.WIDGET_ENABLE, enabled)
    }

    fun setSysUIAodEditorEnabled(enabled: Boolean) {
        updateBooleanSetting(SettingsRepository.Keys.AOD_ENABLE, enabled)
    }

    // AD
    fun setAdWeatherEnabled(enabled: Boolean) {
        updateBooleanSetting(SettingsRepository.Keys.AD_WEATHER, enabled)
    }

    fun setAdDayEnabled(enabled: Boolean) {
        updateBooleanSetting(SettingsRepository.Keys.AD_DAY, enabled)
    }

}