/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.model.my_agent_detail

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MyAgentDetailEquipResponse(
    val id: Int,
    val level: Int,
    val name: String,
    val icon: String,
    val rarity: String,
    val properties: List<MyAgentPropertyResponse>,
    @SerialName("main_properties") val mainProperties: List<MyAgentPropertyResponse>,
    @SerialName("equip_suit") val equipSuit: MyAgentEquipSuit,
    @SerialName("equipment_type") val equipmentType: Int,
    @SerialName("invalid_property_cnt") val invalidPropertyCnt: Int,
    @SerialName("all_hit") val allHit: Boolean
)

@Serializable
data class MyAgentDetailWeaponResponse(
    val id: Int,
    val level: Int,
    val name: String,
    val star: Int,
    val icon: String,
    val rarity: String,
    val properties: List<MyAgentPropertyResponse>,
    @SerialName("main_properties") val mainProperties: List<MyAgentPropertyResponse>,
    @SerialName("talent_title") val talentTitle: String,
    @SerialName("talent_content") val talentContent: String,
    val profession: Int
)

@Serializable
data class MyAgentPropertyResponse(
    @SerialName("property_name") val propertyName: String,
    @SerialName("property_id") val propertyId: Int,
    val base: String,
    val level: Int,
    val valid: Boolean,
    @SerialName("system_id") val systemId: Int,
    val add: Int
)

@Serializable
data class MyAgentEquipSuit(
    @SerialName("suit_id") val suitId: Int,
    val name: String,
    val own: Int,
    val desc1: String,
    val desc2: String
)

val stubMyAgentDetailWeaponResponse = MyAgentDetailWeaponResponse(
    id = 14114,
    level = 60,
    name = "拘束者",
    star = 2,
    icon = "https://act-webstatic.hoyoverse.com/darkmatter/nap/prod_gf_cn/item_icon_u66fwb/31467ac20432a38772f396fdda6ecccb.png",
    rarity = "S",
    properties = listOf(
        MyAgentPropertyResponse(
            propertyName = "衝擊力",
            propertyId = 12202,
            base = "18%",
            level = 0,
            valid = false,
            systemId = 0,
            add = 0
        )
    ),
    mainProperties = listOf(
        MyAgentPropertyResponse(
            propertyName = "基礎攻擊力",
            propertyId = 12101,
            base = "684",
            level = 0,
            valid = false,
            systemId = 0,
            add = 0
        )
    ),
    talentTitle = "束縛上鎖",
    talentContent = "攻擊命中敵人時，<color=#FFFFFF>[普通攻擊]</color>造成的傷害和失衡值提升<color=#2BAD00>7.5%</color>，最多疊加5層，持續8秒，同一招式內最多觸發一次，每層效果單獨結算持續時間。",
    profession = 2
)

val stubEquipResponse = MyAgentDetailEquipResponse(
    id = 31241,
    level = 15,
    name = "震星迪斯可[1]",
    icon = "https://act-webstatic.hoyoverse.com/darkmatter/nap/prod_gf_cn/item_icon_u66fwb/27968286f55d2d47afdd68b1a7f89ab4.png",
    rarity = "S",
    properties = listOf(
        MyAgentPropertyResponse(
            propertyName = "防禦力",
            propertyId = 13102,
            base = "19.2%",
            level = 4,
            valid = false,
            systemId = 131,
            add = 3
        ), MyAgentPropertyResponse(
            propertyName = "防禦力",
            propertyId = 13103,
            base = "15",
            level = 1,
            valid = false,
            systemId = 131,
            add = 0
        ), MyAgentPropertyResponse(
            propertyName = "暴擊率",
            propertyId = 20103,
            base = "2.4%",
            level = 1,
            valid = false,
            systemId = 201,
            add = 0
        ), MyAgentPropertyResponse(
            propertyName = "暴擊傷害",
            propertyId = 21103,
            base = "9.6%",
            level = 2,
            valid = false,
            systemId = 211,
            add = 1
        )
    ),
    mainProperties = listOf(
        MyAgentPropertyResponse(
            propertyName = "生命值",
            propertyId = 11103,
            base = "2200",
            level = 1,
            valid = false,
            systemId = 111,
            add = 0
        )
    ),
    equipSuit = MyAgentEquipSuit(
        suitId = 31200,
        name = "震星迪斯可",
        own = 4,
        desc1 = "衝擊力+6%。",
        desc2 = "<color=#FFFFFF>[普通攻擊]</color>、<color=#FFFFFF>[衝刺攻擊]</color>、<color=#FFFFFF>[閃避反擊]</color>對主要攻擊目標造成的失衡值提升20%。"
    ),
    equipmentType = 1,
    invalidPropertyCnt = 4,
    allHit = false
)


val emptyMyAgentDetailWeaponResponse = MyAgentDetailWeaponResponse(
    id = 0,
    level = 0,
    name = "",
    star = 1,
    icon = "",
    rarity = "D",
    properties = emptyList(),
    mainProperties = emptyList(),
    talentTitle = "",
    talentContent = "",
    profession = 0
)