package com.vama.applemusic.ui.mapper

import com.vama.applemusic.ui.model.GenreUiModel
import com.vama.domain.model.Genre

object GenreDomainMapper : DomainMapper<Genre, GenreUiModel> {
    override fun Genre.mapFromDomainModel(): GenreUiModel {
        return GenreUiModel(
            genreId = genreId,
            name = name,
            url = url
        )
    }
}