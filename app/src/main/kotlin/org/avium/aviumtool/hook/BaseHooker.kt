package org.avium.aviumtool.hook

import com.highcapable.yukihookapi.hook.entity.YukiBaseHooker
import com.highcapable.yukihookapi.hook.log.YLog
import org.avium.aviumtool.utils.GlobalConfig

abstract class BaseHooker(private val targetAppType: AppType?): YukiBaseHooker() {

    open fun onHookFlyme10() {}
    open fun onHookFlyme11() {}
    open fun onHookFlyme12() {}

    private var currentAppType: AppType? = null
    private var currentRomVersion: Int? = null
    enum class AppType(val appPackageName: String) {
        SYSTEM("android"),
        SETTINGS("com.android.settings"),
        SYSTEMUI("com.android.systemui"),
        LAUNCHER("com.meizu.flyme.launcher"),
        PACKAGE_INSTALLER("com.android.packageinstaller"),
        BATTERY("com.meizu.battery"),
        ALIVE_WALLPAPER("com.flyme.alivewallpaper"),
        INPUT("com.sohu.inputmethod.sogou.meizu"),
        UNIVERSAL("<universal>"),
    }

    protected fun processHookers(hookers: List<BaseHooker>) {
        hookers.forEach { hooker ->
            runCatching {
                if (hooker.targetAppType == currentAppType) loadHooker(hooker)
            }.onFailure {
                YLog.error("Failed to load hooker: ${hooker.javaClass.simpleName}", it)
            }
        }
    }

    private inline fun handleRomVersions(
        crossinline onVersionMatched: (version: Int) -> Unit
    ) {
        currentRomVersion?.let { version ->
            onVersionMatched(version)
        } ?: YLog.error("Invalid ROM version")
    }

    override fun onHook() {
        currentRomVersion = runCatching {
            GlobalConfig.FLYME_VERSION
        }.getOrElse {
            YLog.error("Not a valid version: ${GlobalConfig.FLYME_VERSION}")
            null
        }

        currentAppType = runCatching {
            AppType.entries.firstOrNull { it.appPackageName == packageName }
        }.getOrElse {
            YLog.error("Not implemented for app: $packageName")
            null
        }

        handleRomVersions { version ->
            when (version) {
                10 -> onHookFlyme10()
                11 -> onHookFlyme11()
                12 -> onHookFlyme12()
                else -> YLog.error("Unsupported Flyme version: $version")
            }
        }

    }
}

