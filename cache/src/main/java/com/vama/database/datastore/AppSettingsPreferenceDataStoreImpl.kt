package com.vama.database.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

class AppSettingsPreferenceDataStoreImpl(
    private val dataStore: DataStore<Preferences>
) : AppSettingsPreferenceDataStore {

    override suspend fun saveAlbumsCopyright(copyright: String) {
        dataStore.edit {
            it[PreferencesDataStoreKeys.ALBUMS_COPYRIGHT] = copyright
        }
    }

    override suspend fun getAlbumsCopyright(): String {
        return dataStore.data.map {
            it[PreferencesDataStoreKeys.ALBUMS_COPYRIGHT] ?: ""
        }.firstOrNull() ?: ""
    }
}