package org.avium.aviumtool.ui.screens.depthwallpaper

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.collectLatest
import org.avium.aviumtool.R
import org.avium.aviumtool.ui.components.SettingsCard

@Composable
fun DepWallpaperScreen(
    viewModel: DepthWallpaperViewModel = viewModel(),
    snackbarHostState: SnackbarHostState
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(viewModel.snackbarMessage) {
        viewModel.snackbarMessage.collectLatest { message ->
            message?.let {
                snackbarHostState.showSnackbar(it, duration = SnackbarDuration.Short)
                viewModel.snackbarMessageShown()
            }
        }
    }

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? -> viewModel.onImageSelected(uri) }
    )
    val maskPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? -> viewModel.onMaskSelected(uri) }
    )

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
            SettingsCard{
                SelectionItem(
                    title = stringResource(R.string.depthwallpaper_select_wallpaper),
                    summary = uiState.selectedImageUri?.lastPathSegment
                        ?.let { stringResource(R.string.depthwallpaper_image_selected, it) }
                        ?: stringResource(R.string.depthwallpaper_select_wallpaper_summary),
                    isLoading = uiState.isLoading,
                    onClick = { if (!uiState.isLoading) imagePickerLauncher.launch("image/*") }
                )
                SelectionItem(
                    title = stringResource(R.string.depthwallpaper_select_mask),
                    summary = uiState.selectedMaskUri?.lastPathSegment
                        ?.let { stringResource(R.string.depthwallpaper_image_selected, it) }
                        ?: stringResource(R.string.depthwallpaper_select_mask_summary),
                    isLoading = uiState.isLoading,
                    onClick = { if (!uiState.isLoading) maskPickerLauncher.launch("image/*") }
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
            painter = painterResource(id = R.drawable.ic_home_depthwallpaper),
            contentDescription = null,
            modifier = Modifier.size(60.dp),
            tint = Color.Unspecified
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.depthwallpaper_title),
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = stringResource(id = R.string.home_item_depthwallpaper_subtitle),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun SelectionItem(
    title: String,
    summary: String,
    isLoading: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = title, style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = summary,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        if (isLoading) {
            Spacer(modifier = Modifier.width(16.dp))
            CircularProgressIndicator(
                modifier = Modifier.size(24.dp),
                strokeWidth = 2.dp
            )
        }
    }
}