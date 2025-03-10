/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.model.my_agent_detail

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MyAgentDetailResponse(
    @SerialName("retcode") val retCode: Int, val message: String, val data: MyAgentDetailData
)

@Serializable
data class MyAgentDetailData(
    @SerialName("avatar_list") val avatarList: List<MyAgentDetailItemResponse>
)

@Serializable
data class MyAgentDetailItemResponse(
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
    val equip: List<MyAgentDetailEquipResponse>,
    val weapon: MyAgentDetailWeaponResponse? = null,
    val properties: List<MyAgentDetailPropertyResponse>,
    val skills: List<MyAgentDetailSkill>,
    val rank: Int,
    @SerialName("role_vertical_painting_url") val roleVerticalPaintingUrl: String,
    @SerialName("equip_plan_info") val equipPlanInfo: MyAgentDetailEquipPlanResponse? = null,
    @SerialName("us_full_name") val usFullName: String,
    @SerialName("vertical_painting_color") val verticalPaintingColor: String,
    @SerialName("sub_element_type") val subElementType: Int
)

@Serializable
data class MyAgentDetailPropertyResponse(
    @SerialName("property_name") val propertyName: String,
    @SerialName("property_id") val propertyId: Int,
    val base: String,
    val add: String,
    val final: String
)

val stubMyAgentDetailResponse = MyAgentDetailResponse(
    retCode = 0, message = "OK", data = MyAgentDetailData(
        avatarList = listOf(
            MyAgentDetailItemResponse(
                id = 1251,
                level = 60,
                nameMi18n = "青衣",
                fullNameMi18n = "青衣",
                elementType = 203,
                campNameMi18n = "刑偵特勤組",
                avatarProfession = 2,
                rarity = "S",
                groupIconPath = "https://act-webstatic.hoyoverse.com/darkmatter/nap/prod_gf_cn/item_icon_u66fwb/033f6219c3e923be69fe41d80818eb8c.png",
                hollowIconPath = "https://act-webstatic.hoyoverse.com/darkmatter/nap/prod_gf_cn/item_icon_u66fwb/b48ab775e50814d8e30e56f6cf6a55d0.png",
                equip = listOf(stubEquipResponse),
                weapon = stubMyAgentDetailWeaponResponse,
                properties = listOf(
                    MyAgentDetailPropertyResponse(
                        propertyName = "生命值",
                        propertyId = 1,
                        base = "8250",
                        add = "3167",
                        final = "11417"
                    ), MyAgentDetailPropertyResponse(
                        propertyName = "攻擊力",
                        propertyId = 2,
                        base = "1442",
                        add = "416",
                        final = "1858"
                    )
                ),
                skills = listOf(stubMyAgentDetailSkill),
                rank = 1,
                roleVerticalPaintingUrl = "https://act-webstatic.hoyoverse.com/game_record/zzzv2/role_vertical_painting/role_vertical_painting_1251.png",
                equipPlanInfo = stubMyAgentDetailEquipPlanResponse,
                usFullName = "Qing Yi",
                verticalPaintingColor = "#28c79d",
                subElementType = 0
            )
        )
    )
)
