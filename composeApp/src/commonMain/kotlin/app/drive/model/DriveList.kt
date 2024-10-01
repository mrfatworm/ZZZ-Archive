/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.drive.model

import com.mrfatworm.zzzarchive.ZzzConfig
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DriveListResponse(
    val drives: List<DriveListItem>
) {
    fun getDrivesNewToOld(): List<DriveListItem> {
        return drives.reversed()
    }
}

@Serializable
data class DriveListItem(
    val id: Int,
    val name: String,
    @SerialName("is_leak") val isLeak: Boolean
) {
    fun getProfileUrl(): String {
        return "https://raw.githubusercontent.com/${ZzzConfig.ASSET_PATH}/Drive/$id.webp"
    }
}

val stubDriveListResponse = DriveListResponse(
    drives = listOf(
        DriveListItem(
            id = 1,
            name = "搖擺爵士",
            isLeak = false
        ), DriveListItem(
            id = 2,
            name = "混沌重金屬",
            isLeak = false,
        )
    )
)
