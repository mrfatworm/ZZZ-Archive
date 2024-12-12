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
import feature.cover_image.data.database.CoverImagesListDB
import feature.drive.data.database.DrivesListDB
import feature.hoyolab.data.database.HoYoLabAccountDB
import feature.wengine.data.database.WEnginesListDB


actual class RoomDatabaseFactory(
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

    actual fun createAgentListDatabase(): RoomDatabase.Builder<AgentsListDB> {
        return createDB(AgentsListDB::class.java, AgentsListDB.DATABASE_NAME)
    }

    actual fun createWEnginesListDatabase(): RoomDatabase.Builder<WEnginesListDB> {
        return createDB(WEnginesListDB::class.java, WEnginesListDB.DATABASE_NAME)
    }

    actual fun createBangbooListDatabase(): RoomDatabase.Builder<BangbooListDB> {
        return createDB(BangbooListDB::class.java, BangbooListDB.DATABASE_NAME)
    }

    actual fun createDrivesListDatabase(): RoomDatabase.Builder<DrivesListDB> {
        return createDB(DrivesListDB::class.java, DrivesListDB.DATABASE_NAME)
    }

    actual fun createCoverImagesListDatabase(): RoomDatabase.Builder<CoverImagesListDB> {
        return createDB(CoverImagesListDB::class.java, CoverImagesListDB.DATABASE_NAME)
    }

    actual fun createHoYoLabAccountDatabase(): RoomDatabase.Builder<HoYoLabAccountDB> {
        return createDB(HoYoLabAccountDB::class.java, HoYoLabAccountDB.DATABASE_NAME)
    }


}