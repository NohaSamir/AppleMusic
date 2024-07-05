package com.vama.remote.model

data class AlbumsFeedResponse(
    val feed: AlbumsFeed
) : RemoteModel

data class AlbumsFeed(
    val results: List<AlbumRemoteModel>,
    val copyright: String
) : RemoteModel
