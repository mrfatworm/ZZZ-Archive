/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.components.agent

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import feature.hoyolab.model.agent.MyAgentDetailSkill
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import ui.components.cards.ContentCard
import ui.theme.AppTheme
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.img_skill_basic_attack
import zzzarchive.composeapp.generated.resources.img_skill_core_passive
import zzzarchive.composeapp.generated.resources.img_skill_dodge
import zzzarchive.composeapp.generated.resources.img_skill_quick_assist
import zzzarchive.composeapp.generated.resources.img_skill_special_attack
import zzzarchive.composeapp.generated.resources.img_skill_ultimate

@Composable
fun MyAgentSkillCard(
    modifier: Modifier = Modifier,
    skills: List<MyAgentDetailSkill>
) {
    val openedSkill = remember { mutableStateOf<MyAgentDetailSkill?>(null) }
    ContentCard(modifier = modifier) {
        Row(horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.s300)) {
            for ((imgRes, skillType) in LEVELLED_SKILL_SLOTS) {
                val skill = skills.find { it.skillType == skillType }
                MyAgentSkillItem(
                    imgRes = imgRes,
                    level = skill?.level?.toString(),
                    skill = skill,
                    onClick = { openedSkill.value = it }
                )
            }
            Spacer(Modifier.weight(1f))
            val corePassive = skills.find { it.skillType == CORE_PASSIVE_SKILL_TYPE }
            MyAgentSkillItem(
                imgRes = Res.drawable.img_skill_core_passive,
                level = corePassiveLevelChar(corePassive?.level ?: 0),
                skill = corePassive,
                onClick = { openedSkill.value = it }
            )
        }
    }

    openedSkill.value?.let { skill ->
        MyAgentSkillDialog(skill = skill) { openedSkill.value = null }
    }
}

private val LEVELLED_SKILL_SLOTS =
    listOf(
        Res.drawable.img_skill_basic_attack to 0,
        Res.drawable.img_skill_dodge to 2,
        Res.drawable.img_skill_quick_assist to 6,
        Res.drawable.img_skill_special_attack to 1,
        Res.drawable.img_skill_ultimate to 3
    )

@Composable
private fun MyAgentSkillItem(
    imgRes: DrawableResource,
    level: String?,
    skill: MyAgentDetailSkill?,
    onClick: (MyAgentDetailSkill) -> Unit
) {
    // Only a skill that actually carries description items is worth opening a dialog for.
    val openable = skill?.items?.isNotEmpty() == true
    Box(
        Modifier
            .size(AppTheme.size.s48)
            .then(
                if (openable && skill != null) {
                    Modifier.pointerHoverIcon(PointerIcon.Hand).clickable { onClick(skill) }
                } else {
                    Modifier
                }
            )
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(imgRes),
            contentDescription =
                skill?.let { stringResource(skillTypeTextRes(it.skillType)) }
        )
        level?.let {
            Text(
                modifier =
                    Modifier
                        .align(Alignment.BottomEnd)
                        .clip(CircleShape)
                        .background(AppTheme.colors.surface)
                        .padding(
                            horizontal = AppTheme.spacing.s250,
                            vertical = AppTheme.spacing.s100
                        ),
                text = level,
                color = AppTheme.colors.onSurface,
                style = AppTheme.typography.labelSmall
            )
        }
    }
}
