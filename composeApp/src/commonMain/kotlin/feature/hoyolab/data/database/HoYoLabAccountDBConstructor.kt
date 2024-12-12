/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.data.database

import androidx.room.RoomDatabaseConstructor

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object HoYoLabAccountDBConstructor : RoomDatabaseConstructor<HoYoLabAccountDB> {
    override fun initialize(): HoYoLabAccountDB
}