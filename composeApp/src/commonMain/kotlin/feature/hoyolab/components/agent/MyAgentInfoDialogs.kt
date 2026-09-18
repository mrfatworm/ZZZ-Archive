/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.components.agent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import feature.hoyolab.model.agent.MyAgentDetailSkill
import feature.hoyolab.model.agent.MyAgentDetailSkillItem
import feature.hoyolab.model.agent.MyAgentDetailWeapon
import feature.hoyolab.model.agent.MyAgentEquipSuit
import feature.hoyolab.model.agent.MyAgentMindscape
import feature.hoyolab.model.agent.MyAgentSkillAwaken
import org.jetbrains.compose.resources.stringResource
import ui.components.buttons.ZzzIconButton
import ui.components.dialogs.BasicDialog
import ui.theme.AppTheme
import ui.utils.hoYoLabRichText
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.close
import zzzarchive.composeapp.generated.resources.ic_close
import zzzarchive.composeapp.generated.resources.inactive
import zzzarchive.composeapp.generated.resources.locked
import zzzarchive.composeapp.generated.resources.mindscape_cinema
import zzzarchive.composeapp.generated.resources.piece_set
import zzzarchive.composeapp.generated.resources.piece_set_short
import zzzarchive.composeapp.generated.resources.potential_awakening
import zzzarchive.composeapp.generated.resources.w_engine_effect

@Composable
fun MyAgentMindscapeDialog(
    mindscapes: List<MyAgentMindscape>,
    onDismiss: () -> Unit
) {
    InfoDialog(title = stringResource(Res.string.mindscape_cinema), onDismiss = onDismiss) {
        for (mindscape in mindscapes) {
            Section(
                title = "${mindscape.id}. ${mindscape.name}",
                subtitle = if (mindscape.isUnlocked) null else stringResource(Res.string.locked),
                isDimmed = !mindscape.isUnlocked,
                body = mindscape.description
            )
        }
    }
}

@Composable
fun MyAgentAwakenDialog(
    awaken: MyAgentSkillAwaken.Awaken,
    onDismiss: () -> Unit
) {
    InfoDialog(
        title = stringResource(Res.string.potential_awakening),
        subtitle = "${awaken.level} / ${awaken.maxLevel}",
        onDismiss = onDismiss
    ) {
        for (tier in awaken.tiers) {
            // A tier can touch more than one skill, so its heading belongs to the tier rather than
            // to each skill under it.
            val affectedSkills = tier.skills.map { stringResource(skillTypeTextRes(it.skillType)) }
            Section(
                title = tier.name,
                subtitle =
                    if (tier.isUnlocked) {
                        affectedSkills.distinct().joinToString(" / ")
                    } else {
                        stringResource(Res.string.locked)
                    },
                isDimmed = !tier.isUnlocked,
                body = tier.skills.map { it.summary }.filter { it.isNotEmpty() }.joinToString("\n")
            )
            for (skill in tier.skills) {
                for (item in skill.items) {
                    SkillItem(item = item, isDimmed = !tier.isUnlocked)
                }
            }
        }
    }
}

@Composable
fun MyAgentSkillDialog(
    skill: MyAgentDetailSkill,
    onDismiss: () -> Unit
) {
    InfoDialog(
        title = stringResource(skillTypeTextRes(skill.skillType)),
        subtitle = skillLevelLabel(skill.skillType, skill.level),
        onDismiss = onDismiss
    ) {
        for (item in skill.items) {
            SkillItem(item = item, isDimmed = false)
        }
    }
}

@Composable
fun MyAgentWeaponTalentDialog(
    weapon: MyAgentDetailWeapon.MyAgentWeapon,
    onDismiss: () -> Unit
) {
    InfoDialog(title = weapon.name, subtitle = "Lv ${weapon.level}", onDismiss = onDismiss) {
        Section(
            title = weapon.talentTitle.ifEmpty { stringResource(Res.string.w_engine_effect) },
            subtitle = null,
            isDimmed = false,
            body = weapon.talentContent
        )
    }
}

@Composable
fun MyAgentDriveSuitDialog(
    suit: MyAgentEquipSuit.EquipSuit,
    onDismiss: () -> Unit
) {
    InfoDialog(
        title = suit.name,
        subtitle = stringResource(Res.string.piece_set_short, suit.own),
        onDismiss = onDismiss
    ) {
        SuitEffect(pieces = 2, body = suit.desc1, isActive = suit.own >= 2)
        SuitEffect(pieces = 4, body = suit.desc2, isActive = suit.own >= 4)
    }
}

/**
 * HoYoLab returns both set effects whichever is actually running -- a two piece set still carries
 * its four piece text -- so an effect the agent has not reached is dimmed and marked inactive.
 */
@Composable
private fun SuitEffect(
    pieces: Int,
    body: String,
    isActive: Boolean
) {
    if (body.isEmpty()) return
    Section(
        title = stringResource(Res.string.piece_set, pieces),
        subtitle = if (isActive) null else stringResource(Res.string.inactive),
        isDimmed = !isActive,
        body = body
    )
}

@Composable
private fun InfoDialog(
    title: String,
    subtitle: String? = null,
    onDismiss: () -> Unit,
    content: @Composable () -> Unit
) {
    BasicDialog(onDismissRequest = onDismiss) {
        Box(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = AppTheme.spacing.s400, vertical = AppTheme.spacing.s350)
        ) {
            Row(
                modifier = Modifier.align(Alignment.Center),
                horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.s300),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title,
                    color = AppTheme.colors.onSurfaceVariant,
                    style = AppTheme.typography.titleMedium
                )
                subtitle?.let {
                    Text(
                        text = it,
                        color = AppTheme.colors.onSurfaceContainer,
                        style = AppTheme.typography.labelMedium
                    )
                }
            }
            ZzzIconButton(
                modifier = Modifier.align(Alignment.CenterEnd),
                iconRes = Res.drawable.ic_close,
                contentDescriptionRes = Res.string.close
            ) {
                onDismiss()
            }
        }
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(
                    start = AppTheme.spacing.s400,
                    end = AppTheme.spacing.s400,
                    bottom = AppTheme.spacing.s400
                ),
            verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.s400)
        ) {
            content()
        }
    }
}

@Composable
private fun Section(
    title: String,
    subtitle: String?,
    isDimmed: Boolean,
    body: String
) {
    Column(verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.s200)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.s300),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f, fill = false),
                text = title,
                color = if (isDimmed) AppTheme.colors.onSurfaceVariant else AppTheme.colors.onSurfaceContainer,
                style = AppTheme.typography.titleSmall
            )
            subtitle?.let {
                Text(
                    text = it,
                    color = AppTheme.colors.onSurfaceVariant,
                    style = AppTheme.typography.labelSmall
                )
            }
        }
        if (body.isNotEmpty()) {
            BodyText(body, isDimmed)
        }
    }
}

@Composable
private fun SkillItem(
    item: MyAgentDetailSkillItem,
    isDimmed: Boolean
) {
    Column(verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.s200)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.s300),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f, fill = false),
                text = item.title,
                color = if (isDimmed) AppTheme.colors.onSurfaceVariant else AppTheme.colors.onSurfaceContainer,
                style = AppTheme.typography.titleSmall
            )
            if (item.isAwaken) {
                Text(
                    text = stringResource(Res.string.potential_awakening),
                    color = AppTheme.colors.primary,
                    style = AppTheme.typography.labelSmall
                )
            }
        }
        BodyText(item.text, isDimmed)
    }
}

@Composable
private fun BodyText(
    text: String,
    isDimmed: Boolean
) {
    // The markup styles its own keyword spans, so the emphasis colour has to dim with the body --
    // left at full strength, a locked or inactive description keeps reading as an active one.
    val emphasisColor =
        if (isDimmed) AppTheme.colors.onSurfaceVariant else AppTheme.colors.onSurfaceContainer
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = hoYoLabRichText(text, emphasisColor),
        color = if (isDimmed) AppTheme.colors.onSurfaceVariant else AppTheme.colors.onSurface,
        style = AppTheme.typography.bodyMedium
    )
}
