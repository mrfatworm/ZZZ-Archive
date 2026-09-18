/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.components.agent

import org.jetbrains.compose.resources.StringResource
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.basic_attack
import zzzarchive.composeapp.generated.resources.core_passive
import zzzarchive.composeapp.generated.resources.dodge
import zzzarchive.composeapp.generated.resources.quick_assist
import zzzarchive.composeapp.generated.resources.skills
import zzzarchive.composeapp.generated.resources.special_attack
import zzzarchive.composeapp.generated.resources.ultimate

/** HoYoLab's `skill_type` codes, as they appear on the live `avatar/info` response. */
const val CORE_PASSIVE_SKILL_TYPE = 5

fun skillTypeTextRes(skillType: Int): StringResource = when (skillType) {
    0 -> Res.string.basic_attack
    1 -> Res.string.special_attack
    2 -> Res.string.dodge
    3 -> Res.string.ultimate
    CORE_PASSIVE_SKILL_TYPE -> Res.string.core_passive
    6 -> Res.string.quick_assist
    else -> Res.string.skills
}

/** The core passive is levelled A-F in game rather than numerically; below A it shows nothing. */
fun corePassiveLevelChar(level: Int): String? = when (level) {
    2 -> "A"
    3 -> "B"
    4 -> "C"
    5 -> "D"
    6 -> "E"
    7 -> "F"
    else -> null
}

fun skillLevelLabel(
    skillType: Int,
    level: Int
): String = if (skillType == CORE_PASSIVE_SKILL_TYPE) {
    corePassiveLevelChar(level)?.let { "Lv $it" } ?: "Lv $level"
} else {
    "Lv $level"
}
