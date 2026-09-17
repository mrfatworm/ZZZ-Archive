/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package database

import androidx.room.RoomDatabaseConstructor

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object ZzzCacheDBConstructor : RoomDatabaseConstructor<ZzzCacheDB> {
    override fun initialize(): ZzzCacheDB
}
