/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.agent.components

import androidx.compose.runtime.Composable
import feature.agent.model.AgentDetail
import org.jetbrains.compose.resources.stringResource
import ui.components.cards.CardHeader
import ui.components.cards.ContentCard
import ui.components.items.ExpandableItem
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.additional_ability
import zzzarchive.composeapp.generated.resources.assist_follow_up
import zzzarchive.composeapp.generated.resources.basic_attack
import zzzarchive.composeapp.generated.resources.chain_attack
import zzzarchive.composeapp.generated.resources.core_passive
import zzzarchive.composeapp.generated.resources.dash_attack
import zzzarchive.composeapp.generated.resources.defensive_assist
import zzzarchive.composeapp.generated.resources.dodge
import zzzarchive.composeapp.generated.resources.dodge_counter
import zzzarchive.composeapp.generated.resources.ex_special_attack
import zzzarchive.composeapp.generated.resources.quick_assist
import zzzarchive.composeapp.generated.resources.skills
import zzzarchive.composeapp.generated.resources.special_attack
import zzzarchive.composeapp.generated.resources.ultimate

@Composable
fun SkillsCard(agentDetail: AgentDetail) {
    ContentCard(hasDefaultPadding = false) {
        CardHeader(
            title = stringResource(Res.string.skills)
        )
        agentDetail.skills.basicAttack.forEach {
            ExpandableItem(
                title = stringResource(Res.string.basic_attack),
                subtitle = it.name,
                description = it.description
            )
        }
        agentDetail.skills.dodge.forEach {
            ExpandableItem(
                title = stringResource(Res.string.dodge),
                subtitle = it.name,
                description = it.description
            )
        }
        agentDetail.skills.dashAttack.forEach {
            ExpandableItem(
                title = stringResource(Res.string.dash_attack),
                subtitle = it.name,
                description = it.description
            )
        }
        agentDetail.skills.dodgeCounter.forEach {
            ExpandableItem(
                title = stringResource(Res.string.dodge_counter),
                subtitle = it.name,
                description = it.description
            )
        }
        agentDetail.skills.quickAssist.forEach {
            ExpandableItem(
                title = stringResource(Res.string.quick_assist),
                subtitle = it.name,
                description = it.description
            )
        }
        agentDetail.skills.defensiveAssist.forEach {
            ExpandableItem(
                title = stringResource(Res.string.defensive_assist),
                subtitle = it.name,
                description = it.description
            )
        }
        agentDetail.skills.assistFollowUp.forEach {
            ExpandableItem(
                title = stringResource(Res.string.assist_follow_up),
                subtitle = it.name,
                description = it.description
            )
        }
        agentDetail.skills.specialAttack.forEach {
            ExpandableItem(
                title = stringResource(Res.string.special_attack),
                subtitle = it.name,
                description = it.description
            )
        }
        agentDetail.skills.exSpecialAttack.forEach {
            ExpandableItem(
                title = stringResource(Res.string.ex_special_attack),
                subtitle = it.name,
                description = it.description
            )
        }
        agentDetail.skills.chainAttack.forEach {
            ExpandableItem(
                title = stringResource(Res.string.chain_attack),
                subtitle = it.name,
                description = it.description
            )
        }
        agentDetail.skills.ultimate.forEach {
            ExpandableItem(
                title = stringResource(Res.string.ultimate),
                subtitle = it.name,
                description = it.description
            )
        }
        agentDetail.skills.corePassive.forEach {
            ExpandableItem(
                title = stringResource(Res.string.core_passive),
                subtitle = it.name,
                description = it.description
            )
        }
        agentDetail.skills.additionalAbility.forEach {
            ExpandableItem(
                title = stringResource(Res.string.additional_ability),
                subtitle = it.name,
                description = it.description
            )
        }
    }
}