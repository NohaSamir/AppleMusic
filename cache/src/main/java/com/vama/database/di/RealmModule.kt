package com.vama.database.di

import com.vama.database.dao.AlbumsDao
import com.vama.database.dao.AlbumsDaoImpl
import com.vama.database.model.AlbumDbModel
import com.vama.database.model.GenreDbModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RealmModule {
    private const val DATABASE_VERSION = 1L

    @Provides
    @Singleton
    fun provideRealmConfiguration(): RealmConfiguration {
        return RealmConfiguration.Builder(
            setOf(AlbumDbModel::class, GenreDbModel::class)
        ).schemaVersion(DATABASE_VERSION)
            .build()
    }

    @Provides
    @Singleton
    fun provideRealm(config: RealmConfiguration): Realm {
        return Realm.open(config)
    }

    @Provides
    @Singleton
    fun provideAlbumsDao(realm: Realm): AlbumsDao {
        return AlbumsDaoImpl(realm)
    }
}