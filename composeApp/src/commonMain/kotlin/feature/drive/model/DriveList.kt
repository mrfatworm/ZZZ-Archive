/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.drive.model

import com.mrfatworm.zzzarchive.ZzzConfig
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DrivesListResponse(
    val drives: List<DriveListItem>
)

@Serializable
data class DriveListItem(
    val id: Int,
    val name: String,
    @SerialName("is_leak") val isLeak: Boolean,
    @SerialName("piece_set_two")
    val pieceSetTwo: String,
    @SerialName("piece_set_four")
    val pieceSetFour: String
) {
    fun getProfileUrl(): String {
        return "https://raw.githubusercontent.com/${ZzzConfig.ASSET_PATH}/Drive/$id.webp"
    }
}

val emptyDriveListItem = DriveListItem(
    id = 0,
    name = "---",
    isLeak = false,
    pieceSetTwo = "---",
    pieceSetFour = "---"
)

val stubDrivesListResponse = DrivesListResponse(
    drives = listOf(
        DriveListItem(
            id = 1,
            name = "搖擺爵士",
            isLeak = false,
            pieceSetTwo = "能量自動回復提升20%。",
            pieceSetFour = "發動[連攜技]或[終結技]時，全隊造成的傷害提升15%，持續12秒，同名被動效果之間不可疊加。"
        ), DriveListItem(
            id = 2,
            name = "混沌重金屬",
            isLeak = false,
            pieceSetTwo = "以太傷害提升10%。",
            pieceSetFour = "裝備者的暴擊傷害提升20%，隊伍中任意角色觸發[侵蝕]效果的額外傷害時，該增益效果額外提升5.5%，最多疊加6層，持續8秒，重複觸發時重置持續時間。"
        )
    )
)
