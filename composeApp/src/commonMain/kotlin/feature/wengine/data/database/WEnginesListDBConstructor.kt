/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.wengine.data.database

import androidx.room.RoomDatabaseConstructor

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object WEnginesListDBConstructor : RoomDatabaseConstructor<WEnginesListDB> {
    override fun initialize(): WEnginesListDB
}