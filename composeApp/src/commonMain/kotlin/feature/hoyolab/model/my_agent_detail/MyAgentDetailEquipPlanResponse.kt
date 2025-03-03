/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.model.my_agent_detail

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MyAgentDetailEquipPlanResponse(
    val type: Int,
    @SerialName("game_default") val gameDefault: MyAgentDetailEquipPlanGameDefault,
    @SerialName("cultivate_info") val cultivateInfo: MyAgentDetailEquipPlanCultivateInfo,
    @SerialName("custom_info") val customInfo: MyAgentDetailEquipPlanCustomInfo
)

@Serializable
data class MyAgentDetailEquipPlanGameDefault(
    @SerialName("property_list") val propertyList: List<MyAgentDetailEquipPlanProperty>
)

@Serializable
data class MyAgentDetailEquipPlanProperty(
    val id: Int,
    val name: String,
    @SerialName("full_name") val fullName: String,
    @SerialName("system_id") val systemId: Int,
    @SerialName("is_select") val isSelect: Boolean
)

@Serializable
data class MyAgentDetailEquipPlanCultivateInfo(
    val name: String,
    @SerialName("plan_id") val planId: String,
    @SerialName("is_delete") val isDelete: Boolean,
    @SerialName("old_plan") val oldPlan: Boolean
)

@Serializable
data class MyAgentDetailEquipPlanCustomInfo(
    @SerialName("property_list") val propertyList: List<MyAgentDetailEquipPlanProperty>
)

val stubMyAgentDetailEquipPlanResponse = MyAgentDetailEquipPlanResponse(
    type = 1,
    gameDefault = MyAgentDetailEquipPlanGameDefault(
        propertyList = listOf(
            MyAgentDetailEquipPlanProperty(
                id = 4,
                name = "衝擊力",
                fullName = "衝擊力",
                systemId = 4,
                isSelect = false
            )
        )
    ),
    cultivateInfo = MyAgentDetailEquipPlanCultivateInfo(
        name = "",
        planId = "0",
        isDelete = false,
        oldPlan = false
    ),
    customInfo = MyAgentDetailEquipPlanCustomInfo(
        propertyList = listOf(
            MyAgentDetailEquipPlanProperty(
                id = 11103,
                name = "生命值",
                fullName = "生命值",
                systemId = 111,
                isSelect = false
            ),
            MyAgentDetailEquipPlanProperty(
                id = 11102,
                name = "生命值",
                fullName = "生命值百分比",
                systemId = 111,
                isSelect = false
            ),
            MyAgentDetailEquipPlanProperty(
                id = 12103,
                name = "攻擊力",
                fullName = "攻擊力",
                systemId = 121,
                isSelect = false
            ),
            MyAgentDetailEquipPlanProperty(
                id = 12102,
                name = "攻擊力",
                fullName = "攻擊力百分比",
                systemId = 121,
                isSelect = true
            ),
            MyAgentDetailEquipPlanProperty(
                id = 13103,
                name = "防禦力",
                fullName = "防禦力",
                systemId = 131,
                isSelect = false
            ),
            MyAgentDetailEquipPlanProperty(
                id = 13102,
                name = "防禦力",
                fullName = "防禦力百分比",
                systemId = 131,
                isSelect = false
            ),
            MyAgentDetailEquipPlanProperty(
                id = 20103,
                name = "暴擊率",
                fullName = "暴擊率",
                systemId = 201,
                isSelect = true
            ),
            MyAgentDetailEquipPlanProperty(
                id = 21103,
                name = "暴擊傷害",
                fullName = "暴擊傷害",
                systemId = 211,
                isSelect = true
            ),
            MyAgentDetailEquipPlanProperty(
                id = 31203,
                name = "異常精通",
                fullName = "異常精通",
                systemId = 312,
                isSelect = true
            ),
            MyAgentDetailEquipPlanProperty(
                id = 23203,
                name = "穿透值",
                fullName = "穿透值",
                systemId = 232,
                isSelect = false
            )
        )
    )
)
