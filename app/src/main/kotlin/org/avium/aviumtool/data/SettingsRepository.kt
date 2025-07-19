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


}