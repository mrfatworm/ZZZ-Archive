/*
 *  Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 *  License: MIT License
 */

package utils

import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.ic_attack_type_pierce
import zzzarchive.composeapp.generated.resources.ic_attack_type_slash
import zzzarchive.composeapp.generated.resources.ic_attack_type_strike
import zzzarchive.composeapp.generated.resources.ic_help
import zzzarchive.composeapp.generated.resources.pierce
import zzzarchive.composeapp.generated.resources.slash
import zzzarchive.composeapp.generated.resources.strike
import zzzarchive.composeapp.generated.resources.unknown


enum class AgentAttackType(val textRes: StringResource, val iconRes: DrawableResource) {
    Pierce(Res.string.pierce, Res.drawable.ic_attack_type_pierce),
    Slash(Res.string.slash, Res.drawable.ic_attack_type_slash),
    Strike(Res.string.strike, Res.drawable.ic_attack_type_strike),
    None(Res.string.unknown, Res.drawable.ic_help)
}

fun findAgentAttackType(attackType: String): AgentAttackType {
    return AgentAttackType.entries.find { it.name.lowercase() == attackType }
        ?: AgentAttackType.None
}