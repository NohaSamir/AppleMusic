package com.vama.domain.usecase

import com.vama.domain.model.Album
import com.vama.domain.model.Result
import com.vama.domain.repository.AlbumRepository
import javax.inject.Inject

class GetAlbumByIdUseCase @Inject constructor(
    private val albumsRepository: AlbumRepository
) {
    suspend operator fun invoke(id: String): Result<Album> =
        albumsRepository.getAlbum(id)
}