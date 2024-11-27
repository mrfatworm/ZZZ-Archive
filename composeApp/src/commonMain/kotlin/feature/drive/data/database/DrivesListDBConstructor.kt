/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.drive.data.database

import androidx.room.RoomDatabaseConstructor

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object DrivesListDBConstructor : RoomDatabaseConstructor<DrivesListDB> {
    override fun initialize(): DrivesListDB
}