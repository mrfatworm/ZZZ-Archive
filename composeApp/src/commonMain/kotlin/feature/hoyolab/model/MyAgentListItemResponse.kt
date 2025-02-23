/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MyAgentListResponse(
    @SerialName("retcode") val retCode: Int,
    val message: String,
    val data: MyAgentListData
)

@Serializable
data class MyAgentListData(
    @SerialName("avatar_list") val avatarList: List<MyAgentListItemResponse>
)

@Serializable
data class MyAgentListItemResponse(
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
    @SerialName("role_square_url") val roleSquareUrl: String,
    @SerialName("sub_element_type") val subElementType: Int
)

val stubMyAgentListResponse = MyAgentListResponse(
    retCode = 0, message = "OK", data = MyAgentListData(
        avatarList = listOf(
            MyAgentListItemResponse(
                id = 1311,
                level = 60,
                nameMi18n = "Astra Yao",
                fullNameMi18n = "Astra Yao",
                elementType = 205,
                campNameMi18n = "Stars of Lyra",
                avatarProfession = 4,
                rarity = "S",
                groupIconPath = "https://act-webstatic.hoyoverse.com/darkmatter/nap/prod_gf_cn/item_icon_u66fwb/094fb5e6d7b3feb345c67a1c2a40c41f.png",
                hollowIconPath = "https://act-webstatic.hoyoverse.com/darkmatter/nap/prod_gf_cn/item_icon_u66fwb/06f425ff47b194228bfaea7f1a6ac502.png",
                rank = 1,
                isChosen = false,
                roleSquareUrl = "https://act-webstatic.hoyoverse.com/game_record/zzzv2/role_square_avatar/role_square_avatar_1311_3113111.png",
                subElementType = 0
            ),
            MyAgentListItemResponse(
                id = 1251,
                level = 60,
                nameMi18n = "Qingyi",
                fullNameMi18n = "Qingyi",
                elementType = 203,
                campNameMi18n = "Criminal Investigation Special Response Team",
                avatarProfession = 2,
                rarity = "S",
                groupIconPath = "https://act-webstatic.hoyoverse.com/darkmatter/nap/prod_gf_cn/item_icon_u66fwb/033f6219c3e923be69fe41d80818eb8c.png",
                hollowIconPath = "https://act-webstatic.hoyoverse.com/darkmatter/nap/prod_gf_cn/item_icon_u66fwb/b48ab775e50814d8e30e56f6cf6a55d0.png",
                rank = 1,
                isChosen = false,
                roleSquareUrl = "https://act-webstatic.hoyoverse.com/game_record/zzzv2/role_square_avatar/role_square_avatar_1251.png",
                subElementType = 0
            )
        )
    )
)