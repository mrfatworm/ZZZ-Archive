/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.drive.data.mapper

import com.mrfatworm.zzzarchive.ZzzConfig
import feature.drive.data.database.DrivesListItemEntity
import feature.drive.model.DriveListItemResponse

fun DriveListItemResponse.toDriveListEntity(path: String = ZzzConfig.ASSET_PATH): DrivesListItemEntity {
    return DrivesListItemEntity(
        id = id,
        name = name,
        imageUrl = "https://raw.githubusercontent.com/$path/Drive/$id.webp",
        isLeak = isLeak,
        pieceSetTwo = pieceSetTwo,
        pieceSetFour = pieceSetFour
    )
}