/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.wengine.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WEnginesListItemEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val name: String,
    val imageUrl: String,
    val isLeak: Boolean,
    val rarity: Int,
    val specialty: String
)

val stubWEnginesListItemEntity = WEnginesListItemEntity(
    id = 44,
    name = "好鬥的阿炮",
    imageUrl = "https://raw.githubusercontent.com/mrfatworm/ZZZ-Archive-Asset/refs/heads/dev/Asset//W-Engine/Image/44.webp",
    isLeak = false,
    rarity = 4,
    specialty = "support",
)