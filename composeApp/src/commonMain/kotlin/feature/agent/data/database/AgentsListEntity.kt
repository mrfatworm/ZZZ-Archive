/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.agent.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AgentsListItemEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val name: String,
    val fullName: String,
    val imageUrl: String,
    val isLeak: Boolean,
    val rarity: Int,
    val specialty: String,
    val attribute: String,
    val attackType: String,
    val factionId: Int
)