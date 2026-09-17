/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.data.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

/**
 * Deliberately kept out of [database.ZzzCacheDB]. The rows here are credentials the user pasted by
 * hand and cannot be re-fetched, so this database must never take the destructive-migration
 * shortcut the cache database relies on; keeping them apart keeps the two migration policies apart.
 */
@Database(
    entities = [HoYoLabAccountEntity::class],
    version = 1
)
@ConstructedBy(HoYoLabAccountDBConstructor::class)
@TypeConverters(ByteArrayConverter::class)
abstract class HoYoLabAccountDB : RoomDatabase() {
    abstract val hoYoLabAccountDao: HoYoLabAccountDao

    companion object {
        const val DATABASE_NAME = "hoyolab_account_list.db"
    }
}
