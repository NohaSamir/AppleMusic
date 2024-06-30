package com.vama.applemusic.ui.model

import android.os.Parcelable
import androidx.compose.runtime.Stable
import kotlinx.parcelize.Parcelize

@Parcelize
@Stable
data class AlbumUiModel(
    val id: String,
    val name: String,
    val artistName: String,
    val artworkUrl: String,
    val releaseDate: String,
    val genres: List<GenreUiModel>,
    val url: String
) : UiModel, Parcelable