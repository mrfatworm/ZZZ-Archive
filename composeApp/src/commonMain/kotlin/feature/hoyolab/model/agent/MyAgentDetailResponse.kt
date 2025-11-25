/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.model.agent

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MyAgentDetailResponse(
    @SerialName("retcode") val retCode: Int? = null,
    val message: String? = null,
    val data: MyAgentDetailDataResponse? = null
)

@Serializable
data class MyAgentDetailDataResponse(@SerialName("avatar_list") val avatarList: List<MyAgentDetailItemResponse>? = null)

@Serializable
data class MyAgentDetailItemResponse(
    val id: Int? = null,
    val level: Int? = null,
    @SerialName("name_mi18n") val nameMi18n: String? = null,
    @SerialName("full_name_mi18n") val fullNameMi18n: String? = null,
    @SerialName("element_type") val elementType: Int? = null,
    @SerialName("camp_name_mi18n") val campNameMi18n: String? = null,
    @SerialName("avatar_profession") val avatarProfession: Int? = null,
    val rarity: String? = null,
    @SerialName("group_icon_path") val groupIconPath: String? = null,
    @SerialName("hollow_icon_path") val hollowIconPath: String? = null,
    val equip: List<MyAgentDetailEquipResponse>? = null,
    val weapon: MyAgentDetailWeaponResponse? = null,
    val properties: List<MyAgentDetailPropertyResponse>? = null,
    val skills: List<MyAgentDetailSkillResponse>? = null,
    val rank: Int? = null,
    @SerialName("role_vertical_painting_url") val roleVerticalPaintingUrl: String? = null,
    @SerialName("equip_plan_info") val equipPlanInfo: MyAgentDetailEquipPlanResponse? = null,
    @SerialName("us_full_name") val usFullName: String? = null,
    @SerialName("vertical_painting_color") val verticalPaintingColor: String? = null,
    @SerialName("sub_element_type") val subElementType: Int? = null
)

@Serializable
data class MyAgentDetailPropertyResponse(
    @SerialName("property_name") val propertyName: String? = null,
    @SerialName("property_id") val propertyId: Int? = null,
    val base: String? = null,
    val add: String? = null,
    val final: String? = null
)

@Suppress("ktlint:standard:max-line-length")
val stubMyAgentDetailResponse =
    MyAgentDetailResponse(
        retCode = 0,
        message = "OK",
        data =
            MyAgentDetailDataResponse(
                avatarList =
                    listOf(
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
                            properties =
                                listOf(
                                    MyAgentDetailPropertyResponse(
                                        propertyName = "生命值",
                                        propertyId = 1,
                                        base = "8250",
                                        add = "3167",
                                        final = "11417"
                                    ),
                                    MyAgentDetailPropertyResponse(
                                        propertyName = "攻擊力",
                                        propertyId = 2,
                                        base = "1442",
                                        add = "416",
                                        final = "1858"
                                    )
                                ),
                            skills = listOf(stubMyAgentDetailSkillResponse),
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
