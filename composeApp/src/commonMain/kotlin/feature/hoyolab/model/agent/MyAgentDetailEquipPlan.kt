/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.model.agent

import kotlinx.serialization.Serializable

sealed class MyAgentDetailEquipPlan {
    data object Empty : MyAgentDetailEquipPlan()

    data class MyAgentEquipPlan(
        val type: Int,
        val gameDefaultPropertyList: List<EquipPlanProperty>,
        val customInfoPropertyList: List<EquipPlanProperty>,
        val cultivateInfo: CultivateInfo,
        val validPropertyCnt: Int,
        val planOnlySpecialProperty: Boolean,
        val planEffectivePropertyList: List<EquipPlanProperty>
    ) : MyAgentDetailEquipPlan()
}

data class EquipPlanProperty(
    val id: Int,
    val name: String,
    val fullName: String,
    val systemId: Int,
    val isSelect: Boolean
)

@Serializable
data class CultivateInfo(val name: String, val planId: String, val isDelete: Boolean, val oldPlan: Boolean)

val stubMyAgentDetailEquipPlan =
    MyAgentDetailEquipPlan.MyAgentEquipPlan(
        type = 1,
        gameDefaultPropertyList =
            listOf(
                EquipPlanProperty(
                    id = 4,
                    name = "衝擊力",
                    fullName = "衝擊力",
                    systemId = 4,
                    isSelect = false
                )
            ),
        cultivateInfo =
            CultivateInfo(
                name = "",
                planId = "0",
                isDelete = false,
                oldPlan = false
            ),
        customInfoPropertyList =
            listOf(
                EquipPlanProperty(
                    id = 11103,
                    name = "生命值",
                    fullName = "生命值",
                    systemId = 111,
                    isSelect = false
                ),
                EquipPlanProperty(
                    id = 11102,
                    name = "生命值",
                    fullName = "生命值百分比",
                    systemId = 111,
                    isSelect = false
                ),
                EquipPlanProperty(
                    id = 12103,
                    name = "攻擊力",
                    fullName = "攻擊力",
                    systemId = 121,
                    isSelect = false
                ),
                EquipPlanProperty(
                    id = 12102,
                    name = "攻擊力",
                    fullName = "攻擊力百分比",
                    systemId = 121,
                    isSelect = true
                ),
                EquipPlanProperty(
                    id = 13103,
                    name = "防禦力",
                    fullName = "防禦力",
                    systemId = 131,
                    isSelect = false
                ),
                EquipPlanProperty(
                    id = 13102,
                    name = "防禦力",
                    fullName = "防禦力百分比",
                    systemId = 131,
                    isSelect = false
                ),
                EquipPlanProperty(
                    id = 20103,
                    name = "暴擊率",
                    fullName = "暴擊率",
                    systemId = 201,
                    isSelect = true
                ),
                EquipPlanProperty(
                    id = 21103,
                    name = "暴擊傷害",
                    fullName = "暴擊傷害",
                    systemId = 211,
                    isSelect = true
                ),
                EquipPlanProperty(
                    id = 31203,
                    name = "異常精通",
                    fullName = "異常精通",
                    systemId = 312,
                    isSelect = true
                ),
                EquipPlanProperty(
                    id = 23203,
                    name = "穿透值",
                    fullName = "穿透值",
                    systemId = 232,
                    isSelect = false
                )
            ),
        validPropertyCnt = 21,
        planOnlySpecialProperty = false,
        planEffectivePropertyList = emptyList()
    )
