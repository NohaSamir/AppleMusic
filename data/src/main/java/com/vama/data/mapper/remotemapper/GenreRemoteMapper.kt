package com.vama.data.mapper.remotemapper

import com.vama.domain.model.Genre
import com.vama.remote.model.GenreRemoteModel

object GenreRemoteMapper : RemoteMapper<GenreRemoteModel, Genre> {
    override fun GenreRemoteModel.mapFromRemoteModel(): Genre {
        return Genre(
            genreId = genreId,
            name = name,
            url = url
        )
    }
}