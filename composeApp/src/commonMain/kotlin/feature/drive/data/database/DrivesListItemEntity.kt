/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.drive.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DrivesListItemEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val name: String,
    val imageUrl: String,
    val isLeak: Boolean,
    val pieceSetTwo: String,
    val pieceSetFour: String
)


val emptyDriveListItemEntity = DrivesListItemEntity(
    id = 0,
    name = "---",
    imageUrl = "",
    isLeak = false,
    pieceSetTwo = "---",
    pieceSetFour = "---"
)

val stubDrivesListItemEntity = DrivesListItemEntity(
    id = 1,
    name = "搖擺爵士",
    imageUrl = "https://raw.githubusercontent.com/mrfatworm/ZZZ-Archive-Asset/refs/heads/dev/Asset/Drive/1.webp",
    isLeak = false,
    pieceSetTwo = "能量自動回復提升20%。",
    pieceSetFour = "發動[連攜技]或[終結技]時，全隊造成的傷害提升15%，持續12秒，同名被動效果之間不可疊加。"
)