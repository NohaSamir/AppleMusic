package com.vama.applemusic.ui.model

import android.os.Parcelable
import androidx.compose.runtime.Stable
import kotlinx.parcelize.Parcelize

@Parcelize
@Stable
data class GenreUiModel(
    val genreId: String,
    val name: String,
    val url: String
) : UiModel, Parcelable
