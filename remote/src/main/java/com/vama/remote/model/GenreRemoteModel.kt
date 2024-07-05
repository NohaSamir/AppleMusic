package com.vama.remote.model

data class GenreRemoteModel(
    val genreId: String,
    val name: String,
    val url: String
) : RemoteModel
