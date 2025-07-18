package org.avium.aviumtool.utils

object GlobalConfig {
    val FLYME_VERSION by lazy { SystemUtils.getProp("ro.build.flyme.version").toInt() }
}