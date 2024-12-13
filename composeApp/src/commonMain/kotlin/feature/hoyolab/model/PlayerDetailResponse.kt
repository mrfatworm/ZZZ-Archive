/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlayerDetailResponse(
    @SerialName("retcode") val retCode: Int, val message: String, val data: PlayerInfoData
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

val stubPlayerDetailResponse = PlayerDetailResponse(
    retCode = 0, message = "OK", data = PlayerInfoData(
        stats = PlayerInfoStats(
            activeDays = 160,
            avatarNum = 17,
            worldLevelName = "Legendary Proxy",
            curPeriodZoneLayerCount = 0,
            buddyNum = 24,
            achievementCount = 243,
            climbingTowerLayer = 5,
            nextHundredLayer = "Laboratory RuinsPrep Area"
        ),
        avatarList = listOf(
            PlayerInfoAvatar(
                id = 1251,
                level = 60,
                nameMi18n = "Qingyi",
                fullNameMi18n = "Qingyi",
                elementType = 203,
                campNameMi18n = "Criminal Investigation Special Response Team",
                avatarProfession = 2,
                rarity = "S",
                groupIconPath = "https://act-webstatic.hoyoverse.com/darkmatter/nap/prod_gf_cn/item_icon_u642mb/ce27b0fcda10a39f095b1fc1534e8635.png",
                hollowIconPath = "https://act-webstatic.hoyoverse.com/darkmatter/nap/prod_gf_cn/item_icon_u642mb/2d795cd2baf957410b15510a8a2964c3.png",
                rank = 1,
                isChosen = false,
                roleSquareUrl = "https://act-webstatic.hoyoverse.com/game_record/zzzv2/role_square_avatar/role_square_avatar_1251.png"
            ), PlayerInfoAvatar(
                id = 1241,
                level = 60,
                nameMi18n = "Zhu Yuan",
                fullNameMi18n = "Zhu Yuan",
                elementType = 205,
                campNameMi18n = "Criminal Investigation Special Response Team",
                avatarProfession = 2,
                rarity = "S",
                groupIconPath = "https://act-webstatic.hoyoverse.com/darkmatter/nap/prod_gf_cn/item_icon_u642mb/ce27b0fcda10a39f095b1fc1534e8635.png",
                hollowIconPath = "https://act-webstatic.hoyoverse.com/darkmatter/nap/prod_gf_cn/item_icon_u642mb/3f37a88a88536840e6d4601ba05a1114.png",
                rank = 1,
                isChosen = false,
                roleSquareUrl = "https://act-webstatic.hoyoverse.com/game_record/zzzv2/role_square_avatar/role_square_avatar_1241.png"
            )
        ),
        curHeadIconUrl = "https://act-webstatic.hoyoverse.com/darkmatter/nap/prod_gf_cn/item_icon_u642mb/898feb9a5ef77bd02a992c9bfbc5d76d.png",
        awardState = "AwardStateDone",
        gameDataShow = PlayerInfoGameDataShow(
            personalTitle = "Young Lady's Person",
            titleMainColor = "f58661",
            titleBottomColor = "fe357b",
            titleBgUrl = "https://act-webstatic.hoyoverse.com/darkmatter/nap/prod_gf_cn/item_icon_u642mb/1383c6f29f29ef7c79e5bb2a782eb228.png",
            medalList = listOf(
                "https://act-webstatic.hoyoverse.com/darkmatter/nap/prod_gf_cn/item_icon_u642mb/eb7ae51f666fb51d7304914be81b8599.png",
                "https://act-webstatic.hoyoverse.com/darkmatter/nap/prod_gf_cn/item_icon_u642mb/8439b3a2c292af2d50faec333b2dfa8a.png"
            ),
            cardUrl = "https://act-webstatic.hoyoverse.com/darkmatter/nap/prod_gf_cn/item_icon_u642mb/922b5553874bda279b83d6b78798f6a5.png"
        )
    )
)

