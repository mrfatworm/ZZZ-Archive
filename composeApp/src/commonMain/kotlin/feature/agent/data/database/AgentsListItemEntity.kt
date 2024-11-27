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

val stubAgentsListItemEntity = AgentsListItemEntity(
    id = 3,
    name = "貓又",
    fullName = "貓宮 又奈",
    imageUrl = "https://raw.githubusercontent.com/mrfatworm/ZZZ-Archive-Asset/refs/heads/dev/Asset/Agent/Profile/3.webp",
    isLeak = false,
    rarity = 5,
    specialty = "attack",
    attribute = "physical",
    attackType = "slash",
    factionId = 1
)