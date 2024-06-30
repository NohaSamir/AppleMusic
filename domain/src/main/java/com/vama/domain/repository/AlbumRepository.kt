package com.vama.domain.repository

import com.vama.domain.model.AlbumsFeed
import com.vama.domain.model.Result

interface AlbumRepository {
    suspend fun getAlbumsFeed(
        country: String,
        pageSize: Int
    ): Result<AlbumsFeed>
}