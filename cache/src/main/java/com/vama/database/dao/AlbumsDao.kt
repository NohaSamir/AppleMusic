package com.vama.database.dao

import com.vama.database.model.AlbumDbModel
import kotlinx.coroutines.flow.Flow

interface AlbumsDao {
    fun getMostPlayedAlbumsFeeds(): Flow<List<AlbumDbModel>>
    suspend fun updateMostPlayedAlbums(albums: List<AlbumDbModel>)
}