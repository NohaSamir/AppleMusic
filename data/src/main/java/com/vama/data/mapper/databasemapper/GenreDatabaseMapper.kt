package com.vama.data.mapper.databasemapper

import com.vama.database.model.GenreDbModel
import com.vama.domain.model.Genre

object GenreDatabaseMapper : DatabaseMapper<GenreDbModel, Genre> {
    override fun GenreDbModel.mapFromDatabaseModel(): Genre {
        return Genre(
            genreId = genreId,
            name = name,
            url = url
        )
    }

    override fun Genre.mapToDatabaseModel(): GenreDbModel {
        return GenreDbModel().apply {
            genreId = this@mapToDatabaseModel.genreId
            name = this@mapToDatabaseModel.name
            url = this@mapToDatabaseModel.url
        }
    }
}