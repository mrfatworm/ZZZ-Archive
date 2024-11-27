/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.drive.data.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [DrivesListItemEntity::class], version = 1
)
@ConstructedBy(DrivesListDBConstructor::class)
abstract class DrivesListDB : RoomDatabase() {
    abstract val drivesListDao: DrivesListDao

    companion object {
        const val DATABASE_NAME = "drives_list.db"
    }
}