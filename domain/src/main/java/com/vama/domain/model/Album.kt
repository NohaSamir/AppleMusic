package com.vama.domain.model

data class Album(
    val id: String,
    val name: String,
    val artistName: String,
    val artworkUrl: String,
    val releaseDate: String,
    val genres: List<Genre>,
    val url: String
) : DomainModel