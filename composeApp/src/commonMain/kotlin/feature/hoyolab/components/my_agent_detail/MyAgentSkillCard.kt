/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.components.my_agent_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import feature.hoyolab.model.my_agent_detail.MyAgentDetailSkill
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
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
fun MyAgentSkillColumnCard(
    modifier: Modifier = Modifier, skills: List<MyAgentDetailSkill>
) {
    ContentCard(modifier = modifier) {
        Column(verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.s300)) {
            MyAgentSkillItem(
                Res.drawable.img_skill_basic_attack, skills.find { it.skillType == 0 }?.level ?: 0
            )
            MyAgentSkillItem(
                Res.drawable.img_skill_dodge, skills.find { it.skillType == 2 }?.level ?: 0
            )
            MyAgentSkillItem(
                Res.drawable.img_skill_quick_assist, skills.find { it.skillType == 6 }?.level ?: 0
            )
            MyAgentSkillItem(
                Res.drawable.img_skill_special_attack, skills.find { it.skillType == 1 }?.level ?: 0
            )
            MyAgentSkillItem(
                Res.drawable.img_skill_ultimate, skills.find { it.skillType == 3 }?.level ?: 0
            )
            Spacer(Modifier.weight(1f))
            MyAgentSkillItem(
                Res.drawable.img_skill_core_passive, skills.find { it.skillType == 5 }?.level ?: 0
            )
        }
    }
}

@Composable
fun MyAgentSkillRowCard(
    modifier: Modifier = Modifier, skills: List<MyAgentDetailSkill>
) {
    ContentCard(modifier = modifier) {
        Row(horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.s300)) {
            MyAgentSkillItem(
                Res.drawable.img_skill_basic_attack, skills.find { it.skillType == 0 }?.level ?: 0
            )
            MyAgentSkillItem(
                Res.drawable.img_skill_dodge, skills.find { it.skillType == 2 }?.level ?: 0
            )
            MyAgentSkillItem(
                Res.drawable.img_skill_quick_assist, skills.find { it.skillType == 6 }?.level ?: 0
            )
            MyAgentSkillItem(
                Res.drawable.img_skill_special_attack, skills.find { it.skillType == 1 }?.level ?: 0
            )
            MyAgentSkillItem(
                Res.drawable.img_skill_ultimate, skills.find { it.skillType == 3 }?.level ?: 0
            )
            Spacer(Modifier.weight(1f))
            MyAgentSkillItem(
                Res.drawable.img_skill_core_passive, skills.find { it.skillType == 5 }?.level ?: 0
            )
        }
    }
}

@Composable
private fun MyAgentSkillItem(imgRes: DrawableResource, level: Int) {
    Box(Modifier.size(AppTheme.size.s48)) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(imgRes),
            contentDescription = null
        )
        Text(
            modifier = Modifier.align(Alignment.BottomEnd).clip(CircleShape)
                .background(AppTheme.colors.surface).padding(
                    horizontal = AppTheme.spacing.s250, vertical = AppTheme.spacing.s100
                ),
            text = level.toString(),
            color = AppTheme.colors.onSurface,
            style = AppTheme.typography.labelSmall
        )
    }
}