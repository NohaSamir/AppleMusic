package com.vama.applemusic.ui.mapper

import com.vama.applemusic.ui.mapper.GenreDomainMapper.mapFromDomainModel
import com.vama.applemusic.ui.model.AlbumUiModel
import com.vama.domain.model.Album

object AlbumDomainMapper : DomainMapper<Album, AlbumUiModel> {
    override fun Album.mapFromDomainModel(): AlbumUiModel {
        return AlbumUiModel(
            id = id,
            name = name,
            artistName = artistName,
            artworkUrl = artworkUrl,
            releaseDate = releaseDate,
            genres = genres.map { it.mapFromDomainModel() },
            url = url
        )
    }
}