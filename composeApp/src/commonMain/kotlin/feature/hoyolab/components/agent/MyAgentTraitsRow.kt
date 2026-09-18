/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.components.agent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import feature.hoyolab.model.agent.MyAgentDetail
import feature.hoyolab.model.agent.MyAgentSkillAwaken
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import ui.components.cards.ContentCard
import ui.theme.AppTheme
import ui.utils.contentGap
import utils.AgentAttribute
import utils.AgentSubAttribute
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.mindscape_cinema
import zzzarchive.composeapp.generated.resources.potential_awakening

/**
 * Attribute, mindscape cinema and potential awakening, as three small cards in one row under the
 * agent image.
 *
 * Mindscape and awakening used to be badges laid over the artwork, which is why they inverted the
 * surface colours to stay legible on it; off the artwork they do not need that. Each card puts its
 * identity on the left and its value on the right, so the three line up as one row of comparable
 * facts -- the attribute's icon takes the label slot instead of naming itself, since the icon and
 * the value already say what it is.
 */
@Composable
fun MyAgentTraitsRow(
    modifier: Modifier = Modifier,
    agentDetail: MyAgentDetail
) {
    val mindscapes = agentDetail.mindscapes
    val awaken = agentDetail.skillAwaken
    val hasAttribute = agentDetail.attribute != AgentAttribute.None
    if (!hasAttribute && mindscapes.isEmpty() && awaken !is MyAgentSkillAwaken.Awaken) return

    val openMindscapeDialog = remember { mutableStateOf(false) }
    val openAwakenDialog = remember { mutableStateOf(false) }

    Row(
        // Intrinsic height so a label that wraps in one card does not leave the others shorter.
        modifier = modifier.fillMaxWidth().height(IntrinsicSize.Min),
        horizontalArrangement = Arrangement.spacedBy(contentGap())
    ) {
        if (hasAttribute) {
            TraitCard(
                value = stringResource(agentDetail.attributeTextRes()),
                icon = {
                    Icon(
                        modifier = Modifier.size(AppTheme.size.icon),
                        imageVector = vectorResource(agentDetail.attribute.iconRes),
                        contentDescription = null,
                        tint = agentDetail.attribute.getColor(AppTheme.colors)
                    )
                }
            )
        }

        TraitCard(
            modifier = Modifier.weight(1f),
            label = stringResource(Res.string.mindscape_cinema),
            value = "M${agentDetail.rank}",
            onClick = if (mindscapes.isEmpty()) {
                null
            } else {
                { openMindscapeDialog.value = true }
            }
        )

        if (awaken is MyAgentSkillAwaken.Awaken) {
            TraitCard(
                modifier = Modifier.weight(1f),
                label = stringResource(Res.string.potential_awakening),
                value = "${awaken.level} / ${awaken.maxLevel}",
                onClick = { openAwakenDialog.value = true }
            )
        }
    }

    if (openMindscapeDialog.value) {
        MyAgentMindscapeDialog(mindscapes = mindscapes) { openMindscapeDialog.value = false }
    }
    if (openAwakenDialog.value && awaken is MyAgentSkillAwaken.Awaken) {
        MyAgentAwakenDialog(awaken = awaken) { openAwakenDialog.value = false }
    }
}

/**
 * A sub attribute deals the base element's damage, so it keeps the base icon and colour and only
 * replaces the label -- Miyabi reads "Frost", not "Ice".
 */
private fun MyAgentDetail.attributeTextRes() = if (subAttribute == AgentSubAttribute.None) {
    attribute.textRes
} else {
    subAttribute.textRes
}

@Composable
private fun TraitCard(
    modifier: Modifier = Modifier,
    value: String,
    label: String? = null,
    onClick: (() -> Unit)? = null,
    icon: @Composable (() -> Unit)? = null
) {
    ContentCard(modifier = modifier.fillMaxHeight(), hasDefaultPadding = false, onClick = onClick) {
        Row(
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = AppTheme.spacing.s350, vertical = AppTheme.spacing.s300),
            horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.s300),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Identity in the left slot -- a label, or the attribute's icon in its place -- and the
            // value in the right, so all three cards line up whichever they are.
            icon?.invoke()
            if (label != null) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = label,
                    color = AppTheme.colors.onSurfaceVariant,
                    style = AppTheme.typography.labelSmall,
                    // English labels are long enough to need a second line at Compact width.
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Text(
                text = value,
                color = AppTheme.colors.onSurfaceContainer,
                style = AppTheme.typography.labelMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
