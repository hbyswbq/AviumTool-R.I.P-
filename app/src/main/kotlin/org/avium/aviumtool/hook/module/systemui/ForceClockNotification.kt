package org.avium.aviumtool.hook.module.systemui

import com.highcapable.kavaref.KavaRef.Companion.asResolver
import com.highcapable.kavaref.extension.classOf
import org.avium.aviumtool.hook.BaseHooker

object ForceClockNotification : BaseHooker(AppType.SYSTEMUI) {
    override fun onHookFlyme12() {
        "com.flyme.systemui.plugins.clocks.vertical_stretch.VerticalStretchClockImpl".toClass()
            .asResolver().firstMethod {
                name = "setHasNotification"
                parameters(
                    classOf<Boolean>()
                )
            }.hook {
                before { args[0] = true }
            }
    }
}