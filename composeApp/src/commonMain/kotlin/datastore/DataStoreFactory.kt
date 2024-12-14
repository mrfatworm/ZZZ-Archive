/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.SynchronizedObject
import kotlinx.coroutines.internal.synchronized
import okio.Path.Companion.toPath

expect class DataStoreFactory {
    fun getPreferenceDataStore(): DataStore<Preferences>
    fun getConfigDataStore(): DataStore<Preferences>
}

internal const val dataStorePreferenceFileName = "zzz.preferences_pb"
internal const val dataStoreConfigFileName = "zzz.configs_pb"
private lateinit var dataStore: DataStore<Preferences>

@OptIn(InternalCoroutinesApi::class)
private val lock = SynchronizedObject()

/**
 * Gets the singleton DataStore instance, creating it if necessary.
 */
@OptIn(InternalCoroutinesApi::class)
fun createDataStore(producePath: () -> String): DataStore<Preferences> =
    synchronized(lock) {
        if (::dataStore.isInitialized) {
            dataStore
        } else {
            PreferenceDataStoreFactory.createWithPath(produceFile = { producePath().toPath() })
                .also { dataStore = it }
        }
    }



