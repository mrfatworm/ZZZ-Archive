/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package datastore

import android.content.Context

actual class DataStoreFactory(private val context: Context) {
    actual fun getPreferenceDataStore() = createDataStore(
        producePath = { context.filesDir.resolve(dataStorePreferenceFileName).absolutePath }
    )

    actual fun getConfigDataStore() = createDataStore(
        producePath = { context.filesDir.resolve(dataStoreConfigFileName).absolutePath }
    )
}