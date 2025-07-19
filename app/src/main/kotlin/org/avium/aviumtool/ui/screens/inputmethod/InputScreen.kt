package org.avium.aviumtool.ui.screens.inputmethod

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import org.avium.aviumtool.R
import org.avium.aviumtool.ui.SettingsViewModel
import org.avium.aviumtool.ui.components.SettingsCard
import org.avium.aviumtool.ui.screens.systemui.SettingsToggleItem

@Composable
fun InputScreen(viewModel: SettingsViewModel = viewModel()) {

    val input5kEnabled by viewModel.inputMethod5k.collectAsStateWithLifecycle()
    val input150Enabled by viewModel.inputMethod150.collectAsStateWithLifecycle()


    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(vertical = 16.dp)
    ) {
        item {
            SettingsCard {
                HeaderSection()
            }
        }
        item {
            SettingsCard {
                SettingsToggleItem(
                    title = stringResource(id = R.string.input_unlock_5k_switch),
                    checked = input5kEnabled,
                    onCheckedChange = { viewModel.setInput5kEnabled(it) }
                )
                SettingsToggleItem(
                    title = stringResource(id = R.string.input_unlock_150_switch),
                    checked = input150Enabled,
                    onCheckedChange = { viewModel.setInput150Enabled(it) }
                )

            }
        }
    }
}
@Composable
private fun HeaderSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = R.drawable.icon_keyboard),
            contentDescription = null,
            modifier = Modifier.size(60.dp),
            tint = Color.Unspecified
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.input_title),
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = stringResource(id = R.string.system_ui_warning_subtitle),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )
    }
}