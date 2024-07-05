package com.vama.domain.model

data class AlbumsFeed(
    val feeds: List<Album>,
    val copyright: String
) : DomainModel
