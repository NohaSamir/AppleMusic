package com.vama.domain.usecase

import com.vama.domain.model.AlbumsFeed
import com.vama.domain.model.Result
import com.vama.domain.repository.AlbumRepository
import javax.inject.Inject

class GetAlbumsFeedUseCase @Inject constructor(
    private val albumsRepository: AlbumRepository
) {
    companion object {
        const val NUM_OF_ITEMS_TO_LOAD = 100
        const val COUNTRY = "us"
    }

    suspend operator fun invoke(): Result<AlbumsFeed> =
        albumsRepository.getAlbumsFeed(country = COUNTRY, pageSize = NUM_OF_ITEMS_TO_LOAD)
}