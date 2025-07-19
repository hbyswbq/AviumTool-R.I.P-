package org.avium.aviumtool.data

import android.content.Context
import android.net.Uri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.avium.aviumtool.utils.ShellUtils
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class WallpaperRepository(private val context: Context) {

    private suspend fun saveUriToPrivateFile(uri: Uri, targetFileName: String): File = withContext(Dispatchers.IO) {
        val targetFile = File(context.cacheDir, targetFileName)
        context.contentResolver.openInputStream(uri)?.use { input ->
            FileOutputStream(targetFile).use { output ->
                input.copyTo(output)
            }
        } ?: throw IOException("无法从 URI 打开: $uri")
        return@withContext targetFile
    }

    suspend fun replaceWallpaper(imageUri: Uri, maskUri: Uri): Result<Unit> = withContext(Dispatchers.IO) {
        runCatching {
            val tempImageFile = saveUriToPrivateFile(imageUri, "temp_image.png")
            val tempMaskFile = saveUriToPrivateFile(maskUri, "temp_mask.png")

            val targetDir = "/data/local/tmp"
            val finalImageName = "avium_tool_wallpaper.png"
            val finalMaskName = "avium_tool_mask.png"
            val finalImagePath = "$targetDir/$finalImageName"
            val finalMaskPath = "$targetDir/$finalMaskName"

            val shell = ShellUtils.shell
            shell.execCommand(
                listOf(
                    "mkdir -p $targetDir",
                    "cp ${tempImageFile.absolutePath} $finalImagePath",
                    "cp ${tempMaskFile.absolutePath} $finalMaskPath",
                    "chmod 644 $finalImagePath",
                    "chmod 644 $finalMaskPath"
                )
            )

            val getResult = shell.execCommand("settings get secure flyme_sysui_clock_data")
            val currentSettings = getResult.successMsg?.trim()

            if (currentSettings.isNullOrBlank() || currentSettings == "null") {
                throw IOException("无法读取系统时钟设置")
            }

            val escapedImagePath = finalImagePath.replace("/", "\\\\/")
            val escapedMaskPath = finalMaskPath.replace("/", "\\\\/")

            val imageRegex = """"image_path"\s*:\s*"[^"]*"""".toRegex()
            val maskRegex = """"mask_path"\s*:\s*"[^"]*"""".toRegex()

            val newSettings = currentSettings
                .replace(imageRegex, """"image_path":"$escapedImagePath"""")
                .replace(maskRegex, """"mask_path":"$escapedMaskPath"""")

            val putResult = shell.execCommand("settings put secure flyme_sysui_clock_data '$newSettings'")

            if (putResult.result != 0 && !putResult.errorMsg.isNullOrEmpty()) {
                throw IOException("写入设置失败: ${putResult.errorMsg}")
            }
        }
    }
}