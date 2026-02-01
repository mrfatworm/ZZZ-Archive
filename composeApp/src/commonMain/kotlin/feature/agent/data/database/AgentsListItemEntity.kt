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
    val imageUrl: String,
    val isHighlight: Boolean,
    val rarity: Int,
    val specialty: String,
    val attribute: String,
    val factionId: Int,
    val materialId: Int,
    val weeklyMaterialId: Int,
    val portraitCount: Int
)

@Suppress("ktlint:standard:max-line-length")
val stubAgentsListItemEntity =
    AgentsListItemEntity(
        id = 3,
        name = "貓又",
        imageUrl = "https://raw.githubusercontent.com/mrfatworm/ZZZ-Archive-Asset/refs/heads/dev/Asset/Agent/Profile/3.webp",
        isHighlight = false,
        rarity = 5,
        specialty = "attack",
        attribute = "physical",
        factionId = 1,
        materialId = 0,
        weeklyMaterialId = 0,
        portraitCount = 1
    )
