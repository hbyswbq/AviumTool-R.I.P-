package org.avium.aviumtool.utils

import android.os.SystemProperties
import com.highcapable.yukihookapi.hook.log.YLog
import java.io.InputStreamReader

object SystemUtils {
    fun getProp(key: String): String =
        runCatching { SystemProperties.get(key) }.getOrNull().takeUnless { it.isNullOrEmpty() }
            ?: "null"

    fun readKernelNode(node: String): String = runCatching {
        Runtime.getRuntime().exec("cat $node").inputStream.use { stream ->
            InputStreamReader(stream).use { reader ->
                reader.readText()
            }
        }
    }.getOrElse {
        YLog.error("Failed to read $node: ${it.message}")
        "null"
    }

    fun writeKernelNode(data: String, node: String) {
        runCatching {
            Runtime.getRuntime()
                .exec(arrayOf("echo $data > $node"))
                .inputStream
                .close()
        }.onFailure {
            YLog.error("Failed to write $node: ${it.message}")
        }
    }

}