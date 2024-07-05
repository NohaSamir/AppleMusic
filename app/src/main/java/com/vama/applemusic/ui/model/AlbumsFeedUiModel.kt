package com.vama.applemusic.ui.model

import androidx.compose.runtime.Stable
import kotlinx.collections.immutable.PersistentList

@Stable
data class AlbumsFeedUiModel(
    val feeds: PersistentList<AlbumUiModel>,
    val copyright: String
) : UiModel