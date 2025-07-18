package org.avium.aviumtool.hook

import com.highcapable.yukihookapi.YukiHookAPI
import com.highcapable.yukihookapi.annotation.xposed.InjectYukiHookWithXposed
import com.highcapable.yukihookapi.hook.factory.configs
import com.highcapable.yukihookapi.hook.xposed.proxy.IYukiHookXposedInit
import org.avium.aviumtool.BuildConfig
import org.avium.aviumtool.hook.module.systemui.ForceClockNotification

@InjectYukiHookWithXposed
object HookEntry : IYukiHookXposedInit {
    override fun onInit() = configs {
        isDebug = BuildConfig.DEBUG
        debugLog {
            tag = "AviumTool"
            isRecord = true
            elements(TAG, PRIORITY, PACKAGE_NAME, USER_ID)
        }
    }

    object FlymeHookerImpl : BaseHooker(
        AppType.UNIVERSAL
    ) {
        override fun onHook() {
            processHookers(
                listOf(
                    // Systemui
                    ForceClockNotification,
                )
            )
        }
    }

    override fun onHook() = YukiHookAPI.encase {
        loadHooker(FlymeHookerImpl)
    }
}