package com.vama.domain.model

data class Genre(
    val genreId: String,
    val name: String,
    val url: String
) : DomainModel
