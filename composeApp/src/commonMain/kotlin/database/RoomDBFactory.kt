/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package database

import androidx.room.RoomDatabase
import feature.agent.data.database.AgentsListDB

// Ref: https://youtu.be/WT9-4DXUqsM?si=SroWKyQ87aBW97bD
expect class RoomDBFactory {
    fun create(): RoomDatabase.Builder<AgentsListDB>
}