/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.components.agent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.github.panpf.sketch.AsyncImage
import feature.hoyolab.model.agent.MyAgentDetailEquipPlan
import feature.hoyolab.model.agent.MyAgentDetailWeapon
import feature.hoyolab.model.agent.getEquipRatingState
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import ui.components.cards.ContentCard
import ui.theme.AppTheme
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.effective_sub_stats
import zzzarchive.composeapp.generated.resources.ic_do_not_disturb_on
import zzzarchive.composeapp.generated.resources.ic_star
import zzzarchive.composeapp.generated.resources.ic_star_filled
import zzzarchive.composeapp.generated.resources.w_engine_not_equipped

@Composable
fun MyAgentWeaponScoreCard(
    modifier: Modifier = Modifier,
    weapon: MyAgentDetailWeapon,
    equipPlan: MyAgentDetailEquipPlan
) {
    ContentCard(modifier = modifier) {
        Row(horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.s400)) {
            if (weapon is MyAgentDetailWeapon.MyAgentWeapon) {
                MyWeapon(
                    Modifier.weight(1f),
                    weapon.iconUrl,
                    weapon.name,
                    weapon.level,
                    weapon.star
                )
            } else {
                MyWeaponEmpty(Modifier.weight(1f))
            }
            if (equipPlan is MyAgentDetailEquipPlan.MyAgentEquipPlan) {
                Score(equipPlan = equipPlan)
            }
        }
    }
}

@Composable
private fun MyWeapon(
    modifier: Modifier = Modifier,
    imgUrl: String,
    name: String,
    level: Int,
    star: Int
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.s400)
    ) {
        val wEngineSize = 96.dp
        AsyncImage(
            modifier = Modifier.size(wEngineSize),
            uri = imgUrl,
            contentDescription = null
        )
        Column(
            modifier = Modifier.height(wEngineSize),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = name,
                color = AppTheme.colors.onSurfaceContainer,
                style = AppTheme.typography.labelLarge
            )

            Text(
                modifier =
                    Modifier
                        .clip(AppTheme.shape.r300)
                        .background(AppTheme.colors.onSurfaceVariant)
                        .padding(
                            horizontal = AppTheme.spacing.s200,
                            vertical = AppTheme.spacing.s100
                        ),
                text = "Lv $level",
                color = AppTheme.colors.surfaceContainer,
                style = AppTheme.typography.labelMedium
            )
            Row {
                for (i in 1..5) {
                    Icon(
                        modifier = Modifier.size(AppTheme.size.icon),
                        imageVector =
                            vectorResource(
                                if (i <=
                                    star
                                ) {
                                    Res.drawable.ic_star_filled
                                } else {
                                    Res.drawable.ic_star
                                }
                            ),
                        contentDescription = null,
                        tint = AppTheme.colors.onSurfaceContainer
                    )
                }
            }
        }
    }
}

@Composable
private fun MyWeaponEmpty(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.s400),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(AppTheme.size.s48),
            imageVector = vectorResource(Res.drawable.ic_do_not_disturb_on),
            contentDescription = null,
            tint = AppTheme.colors.onSurfaceVariant
        )

        Text(
            modifier =
                Modifier
                    .clip(AppTheme.shape.r300)
                    .background(AppTheme.colors.onSurfaceVariant)
                    .padding(
                        horizontal = AppTheme.spacing.s300,
                        vertical = AppTheme.spacing.s200
                    ),
            text = stringResource(Res.string.w_engine_not_equipped),
            color = AppTheme.colors.surfaceContainer,
            style = AppTheme.typography.labelLarge
        )
    }
}

@Composable
private fun Score(
    modifier: Modifier = Modifier,
    equipPlan: MyAgentDetailEquipPlan.MyAgentEquipPlan
) {
    val validPropertyCnt = equipPlan.validPropertyCnt
    val ratingState = getEquipRatingState(equipRating = equipPlan.equipRating)

    // Hide the Score block if equipRating is ER_Default or null
    if (ratingState != null) {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = ratingState.symbol,
                textAlign = TextAlign.Center,
                color = ratingState.color,
                style = AppTheme.typography.scoreRegular
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.s300),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(Res.string.effective_sub_stats),
                    color = AppTheme.colors.onSurfaceVariant,
                    style = AppTheme.typography.bodyMedium
                )
                Text(
                    text = validPropertyCnt.toString(),
                    color = AppTheme.colors.onSurfaceContainer,
                    style = AppTheme.typography.labelLarge
                )
            }
        }
    }
}
