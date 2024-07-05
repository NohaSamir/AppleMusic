package com.vama.data.repository

import com.vama.data.mapper.databasemapper.AlbumDatabaseMapper.mapFromDatabaseModel
import com.vama.data.mapper.databasemapper.AlbumDatabaseMapper.mapToDatabaseModel
import com.vama.data.mapper.remotemapper.AlbumsFeedsRemoteMapper.mapFromRemoteModel
import com.vama.database.dao.AlbumsDao
import com.vama.database.datastore.AppSettingsPreferenceDataStore
import com.vama.domain.exceptions.CachedDataException
import com.vama.domain.exceptions.NetworkException
import com.vama.domain.model.AlbumsFeed
import com.vama.domain.model.Result
import com.vama.domain.repository.AlbumRepository
import com.vama.remote.service.AlbumService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import javax.inject.Inject

class AlbumRepositoryImpl @Inject constructor(
    private val albumService: AlbumService,
    private val albumsDao: AlbumsDao,
    private val appSettingsPreferenceDataStore: AppSettingsPreferenceDataStore
) : AlbumRepository {

    override suspend fun fetchAndUpdateMostPlayedAlbumsFeed(
        country: String,
        pageSize: Int
    ): Flow<Result<AlbumsFeed>> = channelFlow {
        albumsDao.getMostPlayedAlbumsFeeds().collect { cachedAlbums ->
            send(
                Result.Success(
                    AlbumsFeed(
                        feeds = cachedAlbums.map { it.mapFromDatabaseModel() },
                        copyright = appSettingsPreferenceDataStore.getAlbumsCopyright()
                    )
                )
            )
            fetchAlbumsFeed(
                country = country,
                pageSize = pageSize,
                onSuccess = {
                    appSettingsPreferenceDataStore.saveAlbumsCopyright(it.copyright)
                    updateMostPlayedAlbumsDb(
                        albumsFeed = it,
                        onError = { errorMessage ->
                            send(Result.Error(CachedDataException(errorMessage)))
                        })
                },
                onError = { errorMessage ->
                    send(Result.Error(NetworkException(errorMessage)))
                }
            )
        }
    }

    private suspend fun fetchAlbumsFeed(
        country: String,
        pageSize: Int,
        onSuccess: suspend (albumsFeed: AlbumsFeed) -> Unit,
        onError: suspend (String) -> Unit = {}
    ) = runCatching {
        albumService.getMostPlayedAlbumsFeed(country, pageSize)
    }.onSuccess {
        onSuccess(it.mapFromRemoteModel())
    }.onFailure {
        onError(it.message.orEmpty())
    }

    private suspend fun updateMostPlayedAlbumsDb(
        albumsFeed: AlbumsFeed,
        onError: suspend (String) -> Unit
    ) = runCatching {
        albumsDao.updateMostPlayedAlbums(
            albumsFeed.feeds.map {
                it.mapToDatabaseModel()
            }
        )
    }.onFailure {
        onError((it.message.orEmpty()))
    }
}