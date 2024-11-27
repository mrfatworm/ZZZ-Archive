/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package database

import androidx.room.RoomDatabase
import feature.agent.data.database.AgentsListDB
import feature.bangboo.data.database.BangbooListDB
import feature.cover_image.data.database.CoverImagesListDB
import feature.drive.data.database.DrivesListDB
import feature.wengine.data.database.WEnginesListDB

// Ref: Philipp Lackner’s YouTube Channel
expect class RoomDatabaseFactory {
    fun createAgentListDatabase(): RoomDatabase.Builder<AgentsListDB>
    fun createWEnginesListDatabase(): RoomDatabase.Builder<WEnginesListDB>
    fun createBangbooListDatabase(): RoomDatabase.Builder<BangbooListDB>
    fun createDrivesListDatabase(): RoomDatabase.Builder<DrivesListDB>
    fun createCoverImagesListDatabase(): RoomDatabase.Builder<CoverImagesListDB>

}