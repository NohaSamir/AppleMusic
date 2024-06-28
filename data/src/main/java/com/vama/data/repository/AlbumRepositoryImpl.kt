package com.vama.data.repository

import com.vama.data.mapper.remotemapper.AlbumsFeedsRemoteMapper.mapFromRemoteModel
import com.vama.data.util.runSafeCatching
import com.vama.domain.model.Album
import com.vama.domain.model.AlbumsFeed
import com.vama.domain.model.Result
import com.vama.domain.repository.AlbumRepository
import com.vama.remote.service.AlbumService
import javax.inject.Inject

class AlbumRepositoryImpl @Inject constructor(
    private val albumService: AlbumService
) : AlbumRepository {

    override suspend fun getAlbumsFeed(country: String, pageSize: Int): Result<AlbumsFeed> =
        runSafeCatching {
            albumService.getAlbumsFeed(country, pageSize).mapFromRemoteModel()
        }

    override suspend fun getAlbum(id: String): Result<Album> {
        TODO("Not yet implemented")
    }
}