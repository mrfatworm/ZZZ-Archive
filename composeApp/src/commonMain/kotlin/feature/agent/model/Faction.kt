/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.agent.model

import com.mrfatworm.zzzarchive.ZzzConfig
import org.jetbrains.compose.resources.StringResource
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.angels_of_delusion
import zzzarchive.composeapp.generated.resources.belobog_heavy_industries
import zzzarchive.composeapp.generated.resources.criminal_investigation_special_response_team
import zzzarchive.composeapp.generated.resources.gentle_house
import zzzarchive.composeapp.generated.resources.krampus_compliance_authority
import zzzarchive.composeapp.generated.resources.mockingbird
import zzzarchive.composeapp.generated.resources.obol_squad
import zzzarchive.composeapp.generated.resources.section_6
import zzzarchive.composeapp.generated.resources.silver_squad
import zzzarchive.composeapp.generated.resources.sons_of_calydon
import zzzarchive.composeapp.generated.resources.spook_shack
import zzzarchive.composeapp.generated.resources.stars_of_lyra
import zzzarchive.composeapp.generated.resources.unknown
import zzzarchive.composeapp.generated.resources.victoria_housekeeping
import zzzarchive.composeapp.generated.resources.yunkui_summit

data class Faction(val id: Int) {
    fun getFactionNameRes(): StringResource = when (id) {
        1 -> Res.string.gentle_house
        2 -> Res.string.victoria_housekeeping
        3 -> Res.string.belobog_heavy_industries
        4 -> Res.string.obol_squad
        5 -> Res.string.section_6
        6 -> Res.string.criminal_investigation_special_response_team
        7 -> Res.string.sons_of_calydon
        8 -> Res.string.stars_of_lyra
        9 -> Res.string.silver_squad
        10 -> Res.string.mockingbird
        11 -> Res.string.yunkui_summit
        12 -> Res.string.spook_shack
        13 -> Res.string.krampus_compliance_authority
        14 -> Res.string.angels_of_delusion
        else -> Res.string.unknown
    }

    fun getFactionIconUrl(path: String = ZzzConfig.ASSET_PATH): String =
        "https://raw.githubusercontent.com/$path/Agent/Faction/Icon/$id.webp"

    fun getFactionThumbnailUrl(path: String = ZzzConfig.ASSET_PATH): String =
        "https://raw.githubusercontent.com/$path/Agent/Faction/Thumbnail/$id.webp"

    fun getFactionFullUrl(path: String = ZzzConfig.ASSET_PATH): String =
        "https://raw.githubusercontent.com/$path/Agent/Faction/Full/$id.webp"
}
