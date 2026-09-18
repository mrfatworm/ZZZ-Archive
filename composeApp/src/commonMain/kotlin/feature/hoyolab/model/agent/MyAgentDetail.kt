/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.model.agent

import feature.hoyolab.data.mapper.toMyAgentDetailEquip
import feature.hoyolab.data.mapper.toMyAgentDetailEquipPlan
import feature.hoyolab.data.mapper.toMyAgentDetailSkill
import feature.hoyolab.data.mapper.toMyAgentDetailWeapon
import feature.hoyolab.data.mapper.toMyAgentMindscapes
import feature.hoyolab.data.mapper.toMyAgentSkillAwaken
import feature.hoyolab.data.mapper.toMyAgentSkin
import utils.AgentAttribute
import utils.AgentSpecialty
import utils.AgentSubAttribute
import utils.ZzzRarity

data class MyAgentDetail(
    val id: Int,
    val name: String,
    val level: Int,
    val rank: Int,
    val imageUrl: String,
    /** HoYoLab's `#rrggbb` accent for [imageUrl]; empty when the response left it out. */
    val paintingColor: String,
    val factionImageUrl: String,
    val rarity: ZzzRarity,
    val specialty: AgentSpecialty,
    val attribute: AgentAttribute,
    val subAttribute: AgentSubAttribute,
    val equip: List<MyAgentDetailEquip>,
    val weapon: MyAgentDetailWeapon,
    val properties: List<MyAgentDetailProperty>,
    val skills: List<MyAgentDetailSkill>,
    val equipPlanInfo: MyAgentDetailEquipPlan,
    val skins: List<MyAgentSkin>,
    val mindscapes: List<MyAgentMindscape>,
    val skillAwaken: MyAgentSkillAwaken
)

/** One mindscape cinema tier, unlocked once the agent's rank reaches [id]. */
data class MyAgentMindscape(val id: Int, val name: String, val description: String, val isUnlocked: Boolean)

sealed class MyAgentSkillAwaken {
    data object Empty : MyAgentSkillAwaken()

    data class Awaken(val level: Int, val maxLevel: Int, val tiers: List<MyAgentSkillAwakenTier>) :
        MyAgentSkillAwaken()
}

data class MyAgentSkillAwakenTier(
    val level: Int,
    val name: String,
    val isUnlocked: Boolean,
    val skills: List<MyAgentAwakenSkill>
)

data class MyAgentAwakenSkill(val skillType: Int, val summary: String, val items: List<MyAgentDetailSkillItem>)

/**
 * An outfit the player owns for this agent. HoYoLab always returns at least the default outfit,
 * flagged [isOriginal], so [MyAgentDetail.skins] is only worth offering as a picker past one entry.
 */
data class MyAgentSkin(
    val id: Int,
    val name: String,
    val imageUrl: String,
    /** See [MyAgentDetail.paintingColor]. */
    val paintingColor: String,
    val squareImageUrl: String,
    val isOriginal: Boolean
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

data class MyAgentDetailSkill(val level: Int, val skillType: Int, val items: List<MyAgentDetailSkillItem>)

data class MyAgentDetailSkillItem(val title: String, val text: String, val isAwaken: Boolean)

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
        rank = 1,
        imageUrl = "https://act-webstatic.hoyoverse.com/game_record/zzzv2/role_vertical_painting/role_vertical_painting_1251.png",
        paintingColor = "#28c79d",
        factionImageUrl = "https://act-webstatic.hoyoverse.com/darkmatter/nap/prod_gf_cn/item_icon_u66fwb/033f6219c3e923be69fe41d80818eb8c.png",
        rarity = ZzzRarity.RARITY_S,
        specialty = AgentSpecialty.Stun,
        attribute = AgentAttribute.Electric,
        subAttribute = AgentSubAttribute.Frost,
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
        equipPlanInfo = stubMyAgentDetailEquipPlanResponse.toMyAgentDetailEquipPlan(),
        skins =
            stubMyAgentDetailResponse.data
                ?.avatarList
                ?.first()
                ?.skinList
                ?.map { it.toMyAgentSkin() }
                .orEmpty(),
        mindscapes =
            stubMyAgentDetailResponse.data
                ?.avatarList
                ?.first()
                ?.ranks
                .toMyAgentMindscapes(),
        skillAwaken = stubMyAgentSkillAwakenResponse.toMyAgentSkillAwaken()
    )
