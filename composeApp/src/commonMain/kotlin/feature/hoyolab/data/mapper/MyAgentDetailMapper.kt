/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.data.mapper

import feature.hoyolab.model.my_agent_detail.MyAgentDetailItemResponse
import feature.hoyolab.model.my_agent_detail.MyAgentDetailListItem
import utils.findAgentAttributeFromHoYoLab
import utils.findAgentSpecialtyFromHoYoLab
import utils.findRarityFromHoYoLab

fun MyAgentDetailItemResponse.toMyAgentDetailListItem(): MyAgentDetailListItem {
    return MyAgentDetailListItem(
        id = id,
        name = nameMi18n,
        level = level,
        rank = rank,
        imageUrl = roleVerticalPaintingUrl,
        groupImageUrl = groupIconPath,
        rarity = findRarityFromHoYoLab(rarity),
        specialty = findAgentSpecialtyFromHoYoLab(avatarProfession),
        attribute = findAgentAttributeFromHoYoLab(elementType),
        equip = equip,
        weapon = weapon,
        properties = properties,
        skills = skills,
        equipPlanInfo = equipPlanInfo
    )
}