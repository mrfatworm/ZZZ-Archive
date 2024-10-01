/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.wengine.model

import com.mrfatworm.zzzarchive.ZzzConfig
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import utils.AgentSpecialty
import kotlin.enums.EnumEntries

@Serializable
data class WEnginesListResponse(
    @SerialName("w-engines")
    val wEngines: List<WEngineListItem>
) {
    fun getWEnginesNewToOld(): List<WEngineListItem> {
        return wEngines.reversed()
    }
}

@Serializable
data class WEngineListItem(
    val id: Int,
    val name: String,
    @SerialName("is_leak")
    val isLeak: Boolean,
    val rarity: Int,
    val specialty: String,
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
            id = 1,
            name = "「月相」-望",
            isLeak = false,
            rarity = 3,
            specialty = "attack",
        ),
        WEngineListItem(
            id = 2,
            name = "「月相」-晦",
            isLeak = false,
            rarity = 3,
            specialty = "attack",
        )
    )
)
