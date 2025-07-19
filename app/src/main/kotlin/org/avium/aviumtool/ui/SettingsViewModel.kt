package org.avium.aviumtool.ui

import android.app.Application
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.avium.aviumtool.data.SettingsRepository

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

    fun updateBooleanSetting(key: Preferences.Key<Boolean>, value: Boolean) {
        viewModelScope.launch {
            settingsRepository.setBoolean(key, value)
        }
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