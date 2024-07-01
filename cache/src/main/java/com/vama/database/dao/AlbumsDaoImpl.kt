package com.vama.database.dao

import com.vama.database.model.AlbumDbModel
import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.RealmResults
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AlbumsDaoImpl @Inject constructor(private val realm: Realm) : AlbumsDao {

    override fun getMostPlayedAlbumsFeeds(): Flow<List<AlbumDbModel>> {
        return realm.query<AlbumDbModel>().asFlow()
            .map { results ->
                results.list
            }
    }

    override suspend fun updateMostPlayedAlbums(albums: List<AlbumDbModel>) {
        realm.write {
            // Delete existing albums
            val dbAlbums: RealmResults<AlbumDbModel> = query<AlbumDbModel>().find()
            dbAlbums.takeIf { it.isNotEmpty() }?.let {
                delete(it)
            }

            // Insert new albums
            albums.forEach { copyToRealm(it, UpdatePolicy.ALL) }
        }
    }
}
