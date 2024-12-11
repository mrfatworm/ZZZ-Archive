/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlayerDetailResponse(
    @SerialName("retcode")
    val retCode: Int,
    val message: String,
    val data: PlayerInfoData
)

@Serializable
data class PlayerInfoData(
    val stats: PlayerInfoStats,
    @SerialName("avatar_list") val avatarList: List<PlayerInfoAvatar>,
    @SerialName("cur_head_icon_url") val curHeadIconUrl: String,
    @SerialName("award_state") val awardState: String,
    @SerialName("game_data_show") val gameDataShow: PlayerInfoGameDataShow
)

@Serializable
data class PlayerInfoStats(
    @SerialName("active_days") val activeDays: Int,
    @SerialName("avatar_num") val avatarNum: Int,
    @SerialName("world_level_name") val worldLevelName: String,
    @SerialName("cur_period_zone_layer_count") val curPeriodZoneLayerCount: Int,
    @SerialName("buddy_num") val buddyNum: Int,
    @SerialName("achievement_count") val achievementCount: Int,
    @SerialName("climbing_tower_layer") val climbingTowerLayer: Int,
    @SerialName("next_hundred_layer") val nextHundredLayer: String
)

@Serializable
data class PlayerInfoAvatar(
    val id: Int,
    val level: Int,
    @SerialName("name_mi18n") val nameMi18n: String,
    @SerialName("full_name_mi18n") val fullNameMi18n: String,
    @SerialName("element_type") val elementType: Int,
    @SerialName("camp_name_mi18n") val campNameMi18n: String,
    @SerialName("avatar_profession") val avatarProfession: Int,
    val rarity: String,
    @SerialName("group_icon_path") val groupIconPath: String,
    @SerialName("hollow_icon_path") val hollowIconPath: String,
    val rank: Int,
    @SerialName("is_chosen") val isChosen: Boolean,
    @SerialName("role_square_url") val roleSquareUrl: String
)

@Serializable
data class PlayerInfoGameDataShow(
    @SerialName("personal_title") val personalTitle: String,
    @SerialName("title_main_color") val titleMainColor: String,
    @SerialName("title_bottom_color") val titleBottomColor: String,
    @SerialName("title_bg_url") val titleBgUrl: String,
    @SerialName("medal_list") val medalList: List<String>,
    @SerialName("card_url") val cardUrl: String
)