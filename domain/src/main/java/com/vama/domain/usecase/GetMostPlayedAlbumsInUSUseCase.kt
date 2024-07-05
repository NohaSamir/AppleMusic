package com.vama.domain.usecase

import com.vama.domain.model.AlbumsFeed
import com.vama.domain.model.Result
import com.vama.domain.repository.AlbumRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMostPlayedAlbumsInUSUseCase @Inject constructor(
    private val albumsRepository: AlbumRepository
) {
    companion object {
        const val NUM_OF_ITEMS_TO_LOAD = 100
        const val COUNTRY = "us"
    }

    suspend operator fun invoke(): Flow<Result<AlbumsFeed>> =
        albumsRepository.fetchAndUpdateMostPlayedAlbumsFeed(
            country = COUNTRY,
            pageSize = NUM_OF_ITEMS_TO_LOAD
        )
}