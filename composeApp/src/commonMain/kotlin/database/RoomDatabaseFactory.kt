/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package database

import androidx.room.RoomDatabase
import feature.hoyolab.data.database.HoYoLabAccountDB

// Ref: Philipp Lackner’s YouTube Channel
expect class RoomDatabaseFactory {
    fun createCacheDatabase(): RoomDatabase.Builder<ZzzCacheDB>

    fun createHoYoLabAccountDatabase(): RoomDatabase.Builder<HoYoLabAccountDB>

    /**
     * Removes the pre-consolidation [ZzzCacheDB.LEGACY_DATABASE_NAMES] files left behind on
     * installs that predate [ZzzCacheDB]. Safe to drop once those versions are out of circulation.
     */
    fun deleteLegacyCacheDatabases()
}
