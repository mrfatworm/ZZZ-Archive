/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import feature.agent.data.database.AgentsListDB


actual class RoomDBFactory(
    private val context: Context
) {
    actual fun create(): RoomDatabase.Builder<AgentsListDB> {
        val appContext = context.applicationContext
        val dbFile = appContext.getDatabasePath(AgentsListDB.DATABASE_NAME)

        return Room.databaseBuilder(
            context = appContext,
            name = dbFile.absolutePath
        )
    }
}