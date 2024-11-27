/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.wengine.data.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [WEnginesListItemEntity::class], version = 1
)
@ConstructedBy(WEnginesListDBConstructor::class)
abstract class WEnginesListDB : RoomDatabase() {
    abstract val wEnginesListDao: WEnginesListDao

    companion object {
        const val DATABASE_NAME = "w_engines_list.db"
    }
}