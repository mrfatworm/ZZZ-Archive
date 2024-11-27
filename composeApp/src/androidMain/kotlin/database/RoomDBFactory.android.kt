/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import feature.agent.data.database.AgentsListDB
import feature.bangboo.data.database.BangbooListDB
import feature.drive.data.database.DrivesListDB


actual class RoomDBFactory(
    private val context: Context
) {
    private fun <T : RoomDatabase> createDB(
        databaseClass: Class<T>,
        databaseName: String
    ): RoomDatabase.Builder<T> {
        val appContext = context.applicationContext
        val dbFile = appContext.getDatabasePath(databaseName)

        return Room.databaseBuilder(
            context = appContext,
            klass = databaseClass,
            name = dbFile.absolutePath
        )
    }

    actual fun createAgentListDB(): RoomDatabase.Builder<AgentsListDB> {
        return createDB(AgentsListDB::class.java, AgentsListDB.DATABASE_NAME)
    }

    actual fun createBangbooListDB(): RoomDatabase.Builder<BangbooListDB> {
        return createDB(BangbooListDB::class.java, BangbooListDB.DATABASE_NAME)
    }

    actual fun createDrivesListDB(): RoomDatabase.Builder<DrivesListDB> {
        return createDB(DrivesListDB::class.java, DrivesListDB.DATABASE_NAME)
    }

}