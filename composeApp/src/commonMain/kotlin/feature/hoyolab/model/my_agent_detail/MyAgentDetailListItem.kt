/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.model.my_agent_detail

import utils.AgentAttribute
import utils.AgentSpecialty
import utils.ZzzRarity

data class MyAgentDetailListItem(
    val id: Int,
    val name: String,
    val level: Int,
    val rank: Int,
    val imageUrl: String,
    val groupImageUrl: String,
    val rarity: ZzzRarity,
    val specialty: AgentSpecialty,
    val attribute: AgentAttribute,
    val equip: List<MyAgentDetailEquipResponse>,
    val weapon: MyAgentDetailWeaponResponse?,
    val properties: List<MyAgentDetailPropertyResponse>,
    val skills: List<MyAgentDetailSkill>,
    val equipPlanInfo: MyAgentDetailEquipPlanResponse?
)

val stubMyAgentDetailListItem = MyAgentDetailListItem(
    id = 1251,
    name = "青衣",
    level = 60,
    rank = 1,
    imageUrl = "https://act-webstatic.hoyoverse.com/game_record/zzzv2/role_vertical_painting/role_vertical_painting_1251.png",
    groupImageUrl = "https://act-webstatic.hoyoverse.com/darkmatter/nap/prod_gf_cn/item_icon_u66fwb/033f6219c3e923be69fe41d80818eb8c.png",
    rarity = ZzzRarity.RARITY_S,
    specialty = AgentSpecialty.Stun,
    attribute = AgentAttribute.Electric,
    equip = listOf(stubEquipResponse),
    weapon = stubMyAgentDetailWeaponResponse,
    properties = listOf(
        MyAgentDetailPropertyResponse(
            propertyName = "生命值", propertyId = 1, base = "8250", add = "3167", final = "11417"
        ), MyAgentDetailPropertyResponse(
            propertyName = "攻擊力", propertyId = 2, base = "1442", add = "416", final = "1858"
        )
    ),
    skills = listOf(stubMyAgentDetailSkill),
    equipPlanInfo = null
)

