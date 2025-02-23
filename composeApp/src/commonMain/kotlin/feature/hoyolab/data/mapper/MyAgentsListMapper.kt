/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.data.mapper

import feature.hoyolab.model.MyAgentListItem
import feature.hoyolab.model.MyAgentListItemResponse
import utils.findAgentAttributeFromHoYoLab
import utils.findAgentSpecialtyFromHoYoLab
import utils.findRarityFromHoYoLab

fun MyAgentListItemResponse.toMyAgentListItem(): MyAgentListItem {
    return MyAgentListItem(
        id = id,
        name = nameMi18n,
        level = level,
        rank = rank,
        imageUrl = roleSquareUrl,
        rarity = findRarityFromHoYoLab(rarity),
        specialty = findAgentSpecialtyFromHoYoLab(avatarProfession),
        attribute = findAgentAttributeFromHoYoLab(elementType)
    )
}