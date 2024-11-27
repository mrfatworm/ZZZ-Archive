/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package database

import androidx.room.RoomDatabase
import feature.agent.data.database.AgentsListDB
import feature.bangboo.data.database.BangbooListDB
import feature.drive.data.database.DrivesListDB

// Ref: https://youtu.be/WT9-4DXUqsM?si=SroWKyQ87aBW97bD
expect class RoomDBFactory {
    fun createAgentListDB(): RoomDatabase.Builder<AgentsListDB>
    fun createBangbooListDB(): RoomDatabase.Builder<BangbooListDB>
    fun createDrivesListDB(): RoomDatabase.Builder<DrivesListDB>
}