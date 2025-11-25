/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.model.agent

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MyAgentDetailEquipPlanResponse(
    val type: Int? = null,
    @SerialName("game_default") val gameDefault: GameDefaultResponse? = null,
    @SerialName("cultivate_info") val cultivateInfo: CultivateInfoResponse? = null,
    @SerialName("custom_info") val customInfo: EquipPlanCustomInfoResponse? = null,
    @SerialName("valid_property_cnt") val validPropertyCnt: Int? = null,
    @SerialName("plan_only_special_property") val planOnlySpecialProperty: Boolean? = null,
    @SerialName("plan_effective_property_list") val planEffectivePropertyList: List<EquipPlanPropertyResponse>? = null
)

@Serializable
data class GameDefaultResponse(@SerialName("property_list") val propertyList: List<EquipPlanPropertyResponse>? = null)

@Serializable
data class EquipPlanPropertyResponse(
    val id: Int? = null,
    val name: String? = null,
    @SerialName("full_name") val fullName: String? = null,
    @SerialName("system_id") val systemId: Int? = null,
    @SerialName("is_select") val isSelect: Boolean? = null
)

@Serializable
data class CultivateInfoResponse(
    val name: String? = null,
    @SerialName("plan_id") val planId: String? = null,
    @SerialName("is_delete") val isDelete: Boolean? = null,
    @SerialName("old_plan") val oldPlan: Boolean? = null
)

@Serializable
data class EquipPlanCustomInfoResponse(
    @SerialName("property_list") val propertyList: List<EquipPlanPropertyResponse>? = null
)

val stubMyAgentDetailEquipPlanResponse =
    MyAgentDetailEquipPlanResponse(
        type = 1,
        gameDefault =
            GameDefaultResponse(
                propertyList =
                    listOf(
                        EquipPlanPropertyResponse(
                            id = 4,
                            name = "衝擊力",
                            fullName = "衝擊力",
                            systemId = 4,
                            isSelect = false
                        )
                    )
            ),
        cultivateInfo =
            CultivateInfoResponse(
                name = "",
                planId = "0",
                isDelete = false,
                oldPlan = false
            ),
        customInfo =
            EquipPlanCustomInfoResponse(
                propertyList =
                    listOf(
                        EquipPlanPropertyResponse(
                            id = 11103,
                            name = "生命值",
                            fullName = "生命值",
                            systemId = 111,
                            isSelect = false
                        ),
                        EquipPlanPropertyResponse(
                            id = 11102,
                            name = "生命值",
                            fullName = "生命值百分比",
                            systemId = 111,
                            isSelect = false
                        ),
                        EquipPlanPropertyResponse(
                            id = 12103,
                            name = "攻擊力",
                            fullName = "攻擊力",
                            systemId = 121,
                            isSelect = false
                        ),
                        EquipPlanPropertyResponse(
                            id = 12102,
                            name = "攻擊力",
                            fullName = "攻擊力百分比",
                            systemId = 121,
                            isSelect = true
                        ),
                        EquipPlanPropertyResponse(
                            id = 13103,
                            name = "防禦力",
                            fullName = "防禦力",
                            systemId = 131,
                            isSelect = false
                        ),
                        EquipPlanPropertyResponse(
                            id = 13102,
                            name = "防禦力",
                            fullName = "防禦力百分比",
                            systemId = 131,
                            isSelect = false
                        ),
                        EquipPlanPropertyResponse(
                            id = 20103,
                            name = "暴擊率",
                            fullName = "暴擊率",
                            systemId = 201,
                            isSelect = true
                        ),
                        EquipPlanPropertyResponse(
                            id = 21103,
                            name = "暴擊傷害",
                            fullName = "暴擊傷害",
                            systemId = 211,
                            isSelect = true
                        ),
                        EquipPlanPropertyResponse(
                            id = 31203,
                            name = "異常精通",
                            fullName = "異常精通",
                            systemId = 312,
                            isSelect = true
                        ),
                        EquipPlanPropertyResponse(
                            id = 23203,
                            name = "穿透值",
                            fullName = "穿透值",
                            systemId = 232,
                            isSelect = false
                        )
                    )
            ),
        validPropertyCnt = 21,
        planOnlySpecialProperty = false,
        planEffectivePropertyList = emptyList()
    )
