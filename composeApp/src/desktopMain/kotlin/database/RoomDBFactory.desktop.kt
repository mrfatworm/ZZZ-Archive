/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package database

import androidx.room.Room
import androidx.room.RoomDatabase
import feature.agent.data.database.AgentsListDB
import feature.bangboo.data.database.BangbooListDB
import feature.cover_image.data.database.CoverImagesListDB
import feature.drive.data.database.DrivesListDB
import feature.wengine.data.database.WEnginesListDB
import java.io.File

actual class RoomDatabaseFactory {

    private inline fun <reified T : RoomDatabase> createDB(databaseName: String): RoomDatabase.Builder<T> {
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

        val dbFile = File(appDataDir, databaseName)
        return Room.databaseBuilder(dbFile.absolutePath)
    }

    actual fun createAgentListDatabase(): RoomDatabase.Builder<AgentsListDB> {
        return createDB(AgentsListDB.DATABASE_NAME)
    }

    actual fun createWEnginesListDatabase(): RoomDatabase.Builder<WEnginesListDB> {
        return createDB(WEnginesListDB.DATABASE_NAME)
    }

    actual fun createBangbooListDatabase(): RoomDatabase.Builder<BangbooListDB> {
        return createDB(BangbooListDB.DATABASE_NAME)
    }

    actual fun createDrivesListDatabase(): RoomDatabase.Builder<DrivesListDB> {
        return createDB(DrivesListDB.DATABASE_NAME)
    }

    actual fun createCoverImagesListDatabase(): RoomDatabase.Builder<CoverImagesListDB> {
        return createDB(CoverImagesListDB.DATABASE_NAME)
    }
}