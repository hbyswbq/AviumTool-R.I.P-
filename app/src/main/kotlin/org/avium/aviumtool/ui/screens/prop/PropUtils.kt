package org.avium.aviumtool.ui.screens.prop

import org.avium.aviumtool.utils.ShellUtils

object PropUtils {

    fun setProp(propName: String, enabled: Boolean) {
        val value = if (enabled) "full" else "false"
        ShellUtils.shell.execCommand("setprop $propName $value")
    }

    fun setBooleanProp(propName: String, enabled: Boolean) {
        val value = if (enabled) "true" else "false"
        ShellUtils.shell.execCommand("setprop $propName $value")
    }

    fun setGameFrameRateOverride(fps: Int) {
        ShellUtils.shell.execCommand(
            "setprop ro.surface_flinger.game_default_frame_rate_override $fps"
        )
    }
}