package org.avium.aviumtool.ui.screens.depthwallpaper

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.avium.aviumtool.R
import org.avium.aviumtool.data.WallpaperRepository

data class DepthWallpaperUiState(
    val selectedImageUri: Uri? = null,
    val selectedMaskUri: Uri? = null,
    val isLoading: Boolean = false,
)

class DepthWallpaperViewModel(application: Application) : AndroidViewModel(application) {
    private val wallpaperRepository = WallpaperRepository(application)

    private val _uiState = MutableStateFlow(DepthWallpaperUiState())
    val uiState = _uiState.asStateFlow()

    private val _snackbarMessage = MutableStateFlow<String?>(null)
    val snackbarMessage = _snackbarMessage.asStateFlow()

    fun onImageSelected(uri: Uri?) {
        _uiState.update { it.copy(selectedImageUri = uri) }
        uri?.let { newImageUri ->
            _uiState.value.selectedMaskUri?.let { existingMaskUri ->
                applyWallpaper(newImageUri, existingMaskUri)
            }
        }
    }

    fun onMaskSelected(uri: Uri?) {
        _uiState.update { it.copy(selectedMaskUri = uri) }
        uri?.let { newMaskUri ->
            _uiState.value.selectedImageUri?.let { existingImageUri ->
                applyWallpaper(existingImageUri, newMaskUri)
            }
        }
    }

    private fun applyWallpaper(imageUri: Uri, maskUri: Uri) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            _snackbarMessage.value = getApplication<Application>().getString(R.string.depthwallpaper_applying)
            val result = wallpaperRepository.replaceWallpaper(imageUri, maskUri)
            _uiState.update { it.copy(isLoading = false) }
            if (result.isSuccess) {
                _snackbarMessage.value = getApplication<Application>().getString(R.string.depthwallpaper_apply_success)
            } else {
                _snackbarMessage.value = getApplication<Application>().getString(R.string.depthwallpaper_apply_failed) + ": ${result.exceptionOrNull()?.message}"
            }
        }
    }

    fun snackbarMessageShown() {
        _snackbarMessage.value = null
    }
}