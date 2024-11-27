/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.bangboo.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BangbooListItemEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val name: String,
    val imageUrl: String,
    val isLeak: Boolean,
    val rarity: Int,
    val attribute: String,
)

val stubBangbooListItemEntity = BangbooListItemEntity(
    id = 6,
    name = "共鳴布",
    imageUrl = "https://raw.githubusercontent.com/mrfatworm/ZZZ-Archive-Asset/refs/heads/dev/Asset/Bangboo/Profile/6.webp",
    isLeak = false,
    rarity = 5,
    attribute = "ether"
)