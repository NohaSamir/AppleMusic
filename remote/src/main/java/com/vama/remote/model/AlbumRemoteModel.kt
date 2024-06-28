package com.vama.remote.model

data class AlbumRemoteModel(
    val id: String,
    val name: String,
    val artistName: String,
    val artworkUrl: String,
    val releaseDate: String,
    val genres: List<GenreRemoteModel>,
    val url: String
) : RemoteModel