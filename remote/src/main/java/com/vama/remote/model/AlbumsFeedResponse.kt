package com.vama.remote.model

data class AlbumsFeedResponse(
    val results: List<AlbumRemoteModel>,
    val copyright: String
) : RemoteModel
