package com.vama.domain.repository

import com.vama.domain.model.AlbumsFeed
import com.vama.domain.model.Result
import kotlinx.coroutines.flow.Flow

interface AlbumRepository {
    suspend fun fetchAndUpdateMostPlayedAlbumsFeed(
        country: String,
        pageSize: Int
    ): Flow<Result<AlbumsFeed>>
}