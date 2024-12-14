/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import java.io.File

actual class DataStoreFactory {
    actual fun getPreferenceDataStore(): DataStore<Preferences> {

        val os = System.getProperty("os.name").lowercase()
        val userHome = System.getProperty("user.home")
        val appDataDir = when {
            os.contains("win") -> File(System.getenv("APPDATA"), "ZZZ Archive")
            os.contains("mac") -> File(userHome, "Library/Application Support/ZZZ Archive")
            else -> File(userHome, ".local/share/ZZZ Archive")
        }

        if (!appDataDir.exists()) {
            appDataDir.mkdirs()
        }
        val dbFile = File(appDataDir, dataStorePreferenceFileName)
        return createDataStore { dbFile.absolutePath }
    }

    actual fun getConfigDataStore(): DataStore<Preferences> {

        val os = System.getProperty("os.name").lowercase()
        val userHome = System.getProperty("user.home")
        val appDataDir = when {
            os.contains("win") -> File(System.getenv("APPDATA"), "ZZZ Archive")
            os.contains("mac") -> File(userHome, "Library/Application Support/ZZZ Archive")
            else -> File(userHome, ".local/share/ZZZ Archive")
        }

        if (!appDataDir.exists()) {
            appDataDir.mkdirs()
        }
        val dbFile = File(appDataDir, dataStoreConfigFileName)
        return createDataStore { dbFile.absolutePath }
    }
}