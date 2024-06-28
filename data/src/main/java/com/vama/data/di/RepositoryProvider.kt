package com.vama.data.di

import com.vama.data.repository.AlbumRepositoryImpl
import com.vama.domain.repository.AlbumRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryProvider {

    @Binds
    fun provideAlbumRepository(albumRepositoryImpl: AlbumRepositoryImpl): AlbumRepository
}