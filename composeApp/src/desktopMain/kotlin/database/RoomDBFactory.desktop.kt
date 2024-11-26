/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package database

import androidx.room.Room
import androidx.room.RoomDatabase
import feature.agent.data.database.AgentsListDB
import java.io.File

actual class RoomDBFactory {
    actual fun create(): RoomDatabase.Builder<AgentsListDB> {
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

        val dbFile = File(appDataDir, AgentsListDB.DATABASE_NAME)
        return Room.databaseBuilder(dbFile.absolutePath)
    }
}