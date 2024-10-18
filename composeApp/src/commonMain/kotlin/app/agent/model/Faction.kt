/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.agent.model

import com.mrfatworm.zzzarchive.ZzzConfig
import org.jetbrains.compose.resources.StringResource
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.belobog_heavy_industries
import zzzarchive.composeapp.generated.resources.criminal_investigation_special_response_team
import zzzarchive.composeapp.generated.resources.gentle_house
import zzzarchive.composeapp.generated.resources.obol_squad
import zzzarchive.composeapp.generated.resources.section_6
import zzzarchive.composeapp.generated.resources.sons_of_calydon
import zzzarchive.composeapp.generated.resources.unknown
import zzzarchive.composeapp.generated.resources.victoria_housekeeping

data class Faction(
    val id: Int
) {
    fun getFactionNameRes(): StringResource {
        return when (id) {
            1 -> Res.string.gentle_house
            2 -> Res.string.victoria_housekeeping
            3 -> Res.string.belobog_heavy_industries
            4 -> Res.string.obol_squad
            5 -> Res.string.section_6
            6 -> Res.string.criminal_investigation_special_response_team
            7 -> Res.string.sons_of_calydon
            else -> Res.string.unknown
        }
    }

    fun getFactionIconUrl(path: String = ZzzConfig.ASSET_PATH): String {
        return "https://raw.githubusercontent.com/$path/Agent/Faction/Icon/$id.webp"
    }

    fun getFactionThumbnailUrl(path: String = ZzzConfig.ASSET_PATH): String {
        return "https://raw.githubusercontent.com/$path/Agent/Faction/Thumbnail/$id.webp"
    }

    fun getFactionFullUrl(path: String = ZzzConfig.ASSET_PATH): String {
        return "https://raw.githubusercontent.com/$path/Agent/Faction/Full/$id.webp"
    }
}
