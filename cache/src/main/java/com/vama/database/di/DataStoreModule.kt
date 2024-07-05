package com.vama.database.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.vama.database.datastore.AppSettingsPreferenceDataStore
import com.vama.database.datastore.AppSettingsPreferenceDataStoreImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    private val Context.appPreference by preferencesDataStore(
        name = "app_preferences"
    )

    @Named("app_preferences")
    @Provides
    @Singleton
    fun provideAppDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return context.appPreference
    }

    @Provides
    @Singleton
    fun provideAppSettingsPreferenceDataStore(
        @Named("app_preferences") dataStore: DataStore<Preferences>
    ): AppSettingsPreferenceDataStore {
        return AppSettingsPreferenceDataStoreImpl(dataStore)
    }
}
