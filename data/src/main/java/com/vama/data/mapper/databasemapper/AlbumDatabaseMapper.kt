package com.vama.data.mapper.databasemapper

import com.vama.data.mapper.databasemapper.GenreDatabaseMapper.mapFromDatabaseModel
import com.vama.data.mapper.databasemapper.GenreDatabaseMapper.mapToDatabaseModel
import com.vama.database.model.AlbumDbModel
import com.vama.domain.model.Album
import io.realm.kotlin.ext.toRealmSet

object AlbumDatabaseMapper : DatabaseMapper<AlbumDbModel, Album> {
    override fun AlbumDbModel.mapFromDatabaseModel(): Album {
        return Album(
            id = id,
            name = name,
            artistName = artistName,
            artworkUrl = artworkUrl,
            releaseDate = releaseDate,
            genres = genres.map { it.mapFromDatabaseModel() },
            url = url
        )
    }

    override fun Album.mapToDatabaseModel(): AlbumDbModel {
        return AlbumDbModel().apply {
            id = this@mapToDatabaseModel.id
            name = this@mapToDatabaseModel.name
            artistName = this@mapToDatabaseModel.artistName
            artworkUrl = this@mapToDatabaseModel.artworkUrl
            releaseDate = this@mapToDatabaseModel.releaseDate
            genres = this@mapToDatabaseModel.genres
                .map { it.mapToDatabaseModel() }.toRealmSet()
            url = this@mapToDatabaseModel.url
        }
    }
}