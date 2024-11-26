/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.agent.data.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [AgentsListItemEntity::class], version = 1
)
@ConstructedBy(AgentsListDBConstructor::class)
abstract class AgentsListDB : RoomDatabase() {
    abstract val agentsListDao: AgentsListDao

    companion object {
        const val DATABASE_NAME = "agents_list.db"
    }
}