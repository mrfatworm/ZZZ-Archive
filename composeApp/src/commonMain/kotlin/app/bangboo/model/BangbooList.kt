/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.bangboo.model

import com.mrfatworm.zzzarchive.ZzzConfig
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BangbooListResponse(
    val bangboo: List<BangbooListItem>
) {
    fun getBangbooNewToOld(): List<BangbooListItem> {
        return bangboo.reversed()
    }
}

@Serializable
data class BangbooListItem(
    val id: Int,
    val name: String,
    @SerialName("is_leak") val isLeak: Boolean,
    val rarity: Int,
    val attribute: String
) {
    fun getProfileUrl(): String {
        return "https://raw.githubusercontent.com/${ZzzConfig.ASSET_PATH}/Bangboo/Profile/$id.webp"
    }
}

val stubBangbooListResponse = BangbooListResponse(
    bangboo = listOf(
        BangbooListItem(
            id = 1,
            name = "企鵝布",
            isLeak = false,
            rarity = 5,
            attribute = "ice",
        ), BangbooListItem(
            id = 2,
            name = "巴特勒",
            isLeak = false,
            rarity = 4,
            attribute = "electric",
        )
    )
)
