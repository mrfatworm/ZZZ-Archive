/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.bangboo.data.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [BangbooListItemEntity::class], version = 1
)
@ConstructedBy(BangbooListDBConstructor::class)
abstract class BangbooListDB : RoomDatabase() {
    abstract val bangbooListDao: BangbooListDao

    companion object {
        const val DATABASE_NAME = "bangboo_list.db"
    }
}