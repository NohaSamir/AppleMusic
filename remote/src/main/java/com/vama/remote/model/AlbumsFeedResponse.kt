package com.vama.remote.model

data class AlbumsFeedResponse(
    val feed: List<AlbumRemoteModel>,
    val copyright: String
) : RemoteModel
