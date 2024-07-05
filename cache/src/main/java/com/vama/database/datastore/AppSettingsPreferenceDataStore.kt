package com.vama.database.datastore

interface AppSettingsPreferenceDataStore {
    suspend fun saveAlbumsCopyright(copyright: String)
    suspend fun getAlbumsCopyright(): String
}