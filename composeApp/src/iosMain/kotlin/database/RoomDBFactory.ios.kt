/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package database

import androidx.room.Room
import androidx.room.RoomDatabase
import feature.agent.data.database.AgentsListDB
import feature.bangboo.data.database.BangbooListDB
import feature.drive.data.database.DrivesListDB
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

actual class RoomDBFactory {
    @OptIn(ExperimentalForeignApi::class)
    private fun documentDirectory(): String {
        val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
            directory = NSDocumentDirectory,
            inDomain = NSUserDomainMask,
            appropriateForURL = null,
            create = false,
            error = null
        )
        return requireNotNull(documentDirectory?.path)
    }

    actual fun createAgentListDB(): RoomDatabase.Builder<AgentsListDB> {
        val dbFile = documentDirectory() + "/${AgentsListDB.DATABASE_NAME}"
        return Room.databaseBuilder<AgentsListDB>(
            name = dbFile
        )
    }

    actual fun createBangbooListDB(): RoomDatabase.Builder<BangbooListDB> {
        val dbFile = documentDirectory() + "/${BangbooListDB.DATABASE_NAME}"
        return Room.databaseBuilder<BangbooListDB>(
            name = dbFile
        )
    }

    actual fun createDrivesListDB(): RoomDatabase.Builder<DrivesListDB> {
        val dbFile = documentDirectory() + "/${DrivesListDB.DATABASE_NAME}"
        return Room.databaseBuilder<DrivesListDB>(
            name = dbFile
        )
    }
}