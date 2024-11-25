/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.wengine.model

import com.mrfatworm.zzzarchive.ZzzConfig
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import utils.AgentSpecialty
import kotlin.enums.EnumEntries

@Serializable
data class WEnginesListResponse(
    @SerialName("w-engines")
    val wEngines: List<WEngineListItem>
)

@Serializable
data class WEngineListItem(
    val id: Int,
    val name: String,
    @SerialName("is_leak")
    val isLeak: Boolean,
    val rarity: Int,
    val specialty: String
) {
    fun getImageUrl(): String {
        return "https://raw.githubusercontent.com/${ZzzConfig.ASSET_PATH}/W-Engine/Image/$id.webp"
    }

    fun getSpecialtyEnum(agentSpecialty: EnumEntries<AgentSpecialty> = AgentSpecialty.entries) =
        agentSpecialty.find {
            it.name.lowercase() == specialty.lowercase()
        } ?: AgentSpecialty.None
}

val stubWEnginesListResponse = WEnginesListResponse(
    wEngines = listOf(
        WEngineListItem(
            id = 44,
            name = "好鬥的阿炮",
            isLeak = false,
            rarity = 4,
            specialty = "support",
        ),
        WEngineListItem(
            id = 47,
            name = "玉壺青冰",
            isLeak = false,
            rarity = 5,
            specialty = "stun",
        )
    )
)
