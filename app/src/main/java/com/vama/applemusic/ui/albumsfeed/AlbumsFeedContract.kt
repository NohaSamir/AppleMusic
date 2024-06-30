package com.vama.applemusic.ui.albumsfeed

import androidx.compose.runtime.Stable
import com.vama.applemusic.ui.model.AlbumsFeedUiModel
import com.vama.applemusic.ui.model.UiIntent
import com.vama.applemusic.ui.model.UiState

@Stable
sealed class AlbumsFeedUiState : UiState {
    data object Loading : AlbumsFeedUiState()
    data object Retrying : AlbumsFeedUiState()
    data class Success(val albumsFeed: AlbumsFeedUiModel) : AlbumsFeedUiState()
    data class Error(val message: String) : AlbumsFeedUiState()
}

@Stable
sealed class AlbumsFeedUiIntent : UiIntent {
    data object OnRetryClicked : AlbumsFeedUiIntent()
}