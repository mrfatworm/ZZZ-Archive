/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.model.agent

import feature.hoyolab.data.mapper.toMyAgentDetailEquip
import feature.hoyolab.data.mapper.toMyAgentDetailEquipPlan
import feature.hoyolab.data.mapper.toMyAgentDetailSkill
import feature.hoyolab.data.mapper.toMyAgentDetailWeapon
import utils.AgentAttribute
import utils.AgentSpecialty
import utils.ZzzRarity

data class MyAgentDetail(
    val id: Int,
    val name: String,
    val level: Int,
    val mindscapes: Int,
    val imageUrl: String,
    val factionImageUrl: String,
    val rarity: ZzzRarity,
    val specialty: AgentSpecialty,
    val attribute: AgentAttribute,
    val equip: List<MyAgentDetailEquip>,
    val weapon: MyAgentDetailWeapon,
    val properties: List<MyAgentDetailProperty>,
    val skills: List<MyAgentDetailSkill>,
    val equipPlanInfo: MyAgentDetailEquipPlan
)
data class MyAgentDetailEquip(
    val id: Int,
    val level: Int,
    val name: String,
    val iconUrl: String,
    val rarity: String,
    val subProperties: List<MyAgentDriveProperty>,
    val mainProperties: List<MyAgentDriveProperty>,
    val equipSuit: MyAgentEquipSuit,
    val equipmentType: Int,
    val invalidPropertyCnt: Int,
    val allHit: Boolean
)

sealed class MyAgentEquipSuit {
    data object Empty : MyAgentEquipSuit()
    data class EquipSuit(val suitId: Int, val name: String, val own: Int, val desc1: String, val desc2: String) :
        MyAgentEquipSuit()
}

sealed class MyAgentDetailWeapon {
    data object Empty : MyAgentDetailWeapon()
    data class MyAgentWeapon(
        val id: Int,
        val level: Int,
        val name: String,
        val star: Int,
        val iconUrl: String,
        val rarity: String,
        val properties: List<MyAgentDriveProperty>,
        val mainProperties: List<MyAgentDriveProperty>,
        val talentTitle: String,
        val talentContent: String,
        val profession: Int
    ) : MyAgentDetailWeapon()
}

data class MyAgentDetailSkill(val level: Int, val skillType: Int, val items: List<MyAgentDetailSkillItemResponse>)

data class MyAgentDetailProperty(val id: Int, val name: String, val base: String, val add: String, val final: String)

data class MyAgentDriveProperty(
    val id: Int,
    val name: String,
    val base: String,
    val level: Int,
    val valid: Boolean,
    val systemId: Int,
    val add: Int
)

@Suppress("ktlint:standard:max-line-length")
val stubMyAgentDetail =
    MyAgentDetail(
        id = 1251,
        name = "青衣",
        level = 60,
        mindscapes = 1,
        imageUrl = "https://act-webstatic.hoyoverse.com/game_record/zzzv2/role_vertical_painting/role_vertical_painting_1251.png",
        factionImageUrl = "https://act-webstatic.hoyoverse.com/darkmatter/nap/prod_gf_cn/item_icon_u66fwb/033f6219c3e923be69fe41d80818eb8c.png",
        rarity = ZzzRarity.RARITY_S,
        specialty = AgentSpecialty.Stun,
        attribute = AgentAttribute.Electric,
        equip = listOf(stubEquipResponse.toMyAgentDetailEquip()),
        weapon = stubMyAgentDetailWeaponResponse.toMyAgentDetailWeapon(),
        properties =
            listOf(
                MyAgentDetailProperty(
                    name = "生命值",
                    id = 1,
                    base = "8250",
                    add = "3167",
                    final = "11417"
                ),
                MyAgentDetailProperty(
                    name = "攻擊力",
                    id = 2,
                    base = "1442",
                    add = "416",
                    final = "1858"
                )
            ),
        skills = listOf(stubMyAgentDetailSkillResponse.toMyAgentDetailSkill()),
        equipPlanInfo = stubMyAgentDetailEquipPlanResponse.toMyAgentDetailEquipPlan()
    )
