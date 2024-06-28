package com.vama.data.mapper.remotemapper

import com.vama.data.mapper.remotemapper.GenreRemoteMapper.mapFromRemoteModel
import com.vama.domain.model.Album
import com.vama.remote.model.AlbumRemoteModel

object AlbumRemoteMapper : RemoteMapper<AlbumRemoteModel, Album> {
    override fun AlbumRemoteModel.mapFromRemoteModel(): Album {
        return Album(
            id = id,
            name = name,
            artistName = artistName,
            artworkUrl = artworkUrl100,
            releaseDate = releaseDate,
            genres = genres.map { it.mapFromRemoteModel() },
            url = url
        )
    }
}