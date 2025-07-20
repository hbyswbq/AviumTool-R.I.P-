package org.avium.aviumtool

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import org.avium.aviumtool.ui.theme.AviumToolTheme
import org.avium.aviumtool.utils.SystemUtils

class MainActivity : ComponentActivity() {

    private var showCompatibilityWarning = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // check
        checkDeviceCompatibility()

        setContent {
            AviumToolTheme {
                MainScreen()
                CompatibilityWarningDialog()
            }
        }
    }

    private fun checkDeviceCompatibility() {
        val displayId = SystemUtils.getProp("ro.build.display.id")
        if (!displayId.contains("Flyme", ignoreCase = true)) {
            showCompatibilityWarning = true
        }
    }

    @SuppressLint("ContextCastToActivity")
    @Composable
    private fun CompatibilityWarningDialog() {
        var showDialog by remember { mutableStateOf(showCompatibilityWarning) }
        val activity = (LocalContext.current as? Activity)

        if (showDialog) {
            AlertDialog(
                onDismissRequest = {
                },
                title = {
                    Text(text = stringResource(id = R.string.dialog_non_flyme_title))
                },
                text = {
                    Text(text = stringResource(id = R.string.dialog_non_flyme_message))
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            showDialog = false // 忽略
                        }
                    ) {
                        Text(stringResource(id = R.string.dialog_action_ignore))
                    }
                },
                // exit
                dismissButton = {
                    TextButton(
                        onClick = {
                            activity?.finish() // 关闭
                        }
                    ) {
                        Text(stringResource(id = R.string.dialog_action_exit))
                    }
                }
            )
        }
    }
}