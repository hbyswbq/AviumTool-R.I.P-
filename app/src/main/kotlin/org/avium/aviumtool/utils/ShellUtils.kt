package org.avium.aviumtool.utils

import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader

private const val COMMAND_SU = "su"
private const val COMMAND_SH = "sh"
private const val COMMAND_EXIT = "exit\n"
private const val COMMAND_LINE_END = "\n"

class ShellUtils private constructor() {

    data class CommandResult(
        val result: Int,
        val successMsg: String?,
        val errorMsg: String?
    )

    @Suppress("unused")
    fun checkRootPermission() =
        execCommand("echo root", isRoot = true, isNeedResultMsg = false).result == 0

    fun execCommand(
        command: String,
        isRoot: Boolean = true,
        isNeedResultMsg: Boolean = true
    ) = executeCommands(listOf(command), isRoot, isNeedResultMsg)

    @Suppress("unused")
    fun execCommand(
        commands: Iterable<String>,
        isRoot: Boolean = true,
        isNeedResultMsg: Boolean = true
    ) = executeCommands(commands.toList(), isRoot, isNeedResultMsg)


    @Suppress("unused")
    fun execCommand(
        commands: Array<String>,
        isRoot: Boolean = true,
        isNeedResultMsg: Boolean = true
    ) = executeCommands(commands.toList(), isRoot, isNeedResultMsg)


    private fun executeCommands(
        commands: List<String>,
        isRoot: Boolean,
        isNeedResultMsg: Boolean
    ): CommandResult {
        if (commands.isEmpty()) return CommandResult(-1, null, null)

        return runCatching {
            val process = Runtime.getRuntime().exec(if (isRoot) COMMAND_SU else COMMAND_SH)

            DataOutputStream(process.outputStream).use { os ->
                commands.forEach { cmd ->
                    os.write(cmd.toByteArray())
                    os.writeBytes(COMMAND_LINE_END)
                    os.flush()
                }
                os.writeBytes(COMMAND_EXIT)
                os.flush()
            }

            val exitCode = process.waitFor()

            if (!isNeedResultMsg) return CommandResult(exitCode, null, null)

            val successMsg = process.inputStream.use { input ->
                BufferedReader(InputStreamReader(input)).use(BufferedReader::readText)
            }

            val errorMsg = process.errorStream.use { error ->
                BufferedReader(InputStreamReader(error)).use(BufferedReader::readText)
            }

            CommandResult(exitCode, successMsg, errorMsg)
        }.getOrElse {
            it.printStackTrace()
            CommandResult(-1, null, null)
        }
    }

    companion object {
        val shell by lazy { ShellUtils() }
    }
}