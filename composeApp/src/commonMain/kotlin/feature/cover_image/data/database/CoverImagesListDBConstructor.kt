/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.cover_image.data.database

import androidx.room.RoomDatabaseConstructor

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object CoverImagesListDBConstructor : RoomDatabaseConstructor<CoverImagesListDB> {
    override fun initialize(): CoverImagesListDB
}