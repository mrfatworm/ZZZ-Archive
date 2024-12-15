/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.hoyolab.components

import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import feature.hoyolab.model.GameRecordState
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import ui.components.ImageNotFound
import ui.components.OutlinedText
import ui.components.buttons.ZzzOutlineButton
import ui.components.cards.ContentCard
import ui.theme.AppTheme
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.add_account
import zzzarchive.composeapp.generated.resources.bounty_commissions
import zzzarchive.composeapp.generated.resources.check_in
import zzzarchive.composeapp.generated.resources.check_in_success
import zzzarchive.composeapp.generated.resources.engagement_today
import zzzarchive.composeapp.generated.resources.ic_bolt
import zzzarchive.composeapp.generated.resources.ic_calendar_clock
import zzzarchive.composeapp.generated.resources.ic_check_circle
import zzzarchive.composeapp.generated.resources.ic_link
import zzzarchive.composeapp.generated.resources.ic_warning
import zzzarchive.composeapp.generated.resources.img_hoyolab_card_preview
import zzzarchive.composeapp.generated.resources.investigate_point
import zzzarchive.composeapp.generated.resources.not_operating
import zzzarchive.composeapp.generated.resources.operating
import zzzarchive.composeapp.generated.resources.purchasable
import zzzarchive.composeapp.generated.resources.purchased
import zzzarchive.composeapp.generated.resources.ready_to_settle
import zzzarchive.composeapp.generated.resources.ridu_weekly
import zzzarchive.composeapp.generated.resources.scratch_card
import zzzarchive.composeapp.generated.resources.user_profile_image
import zzzarchive.composeapp.generated.resources.video_store


val minFlowRowElementWidth = 200.dp

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HoYoLabCard(
    uiState: GameRecordState,
    signResult: String?,
    onSignClick: () -> Unit,
    onAddAccountClick: () -> Unit
) {
    val hasAccount = uiState.uid != "0000000000"
    ContentCard(modifier = Modifier.fillMaxWidth()) {
        Header(uiState, hasAccount, signResult, onSignClick, onAddAccountClick)
        Spacer(Modifier.size(AppTheme.spacing.s400))
        if (hasAccount) {
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.s300),
                verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.s300)
            ) {
                DailyMission(Modifier.weight(1f), uiState)
                WeeklyMission(Modifier.weight(1f), uiState)
            }
        }
    }
}

@Composable
private fun Header(
    uiState: GameRecordState,
    hasAccount: Boolean,
    signResult: String?,
    onSignClick: () -> Unit,
    onAddAccountClick: () -> Unit
) {
    Box(modifier = Modifier.clip(AppTheme.shape.r400).height(120.dp)) {
        SubcomposeAsyncImage(modifier = Modifier.fillMaxSize().clip(AppTheme.shape.r400),
            model = uiState.cardUrl,
            contentDescription = stringResource(Res.string.user_profile_image),
            contentScale = ContentScale.Crop,
            filterQuality = FilterQuality.High,
            error = {
                if (hasAccount) {
                    ImageNotFound()
                } else {
                    Image(
                        modifier = Modifier.fillMaxSize().clip(AppTheme.shape.r400),
                        painter = painterResource(Res.drawable.img_hoyolab_card_preview),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                    )
                }
            })
        Column(
            modifier = Modifier.fillMaxSize().padding(AppTheme.spacing.s300),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            SignButton(signResult, onSignClick)
            Row(
                horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.s300),
                verticalAlignment = Alignment.Bottom
            ) {
                PlayerInfo(Modifier.weight(1f), uiState)
                PlayerEnergy(uiState)
            }
        }
        if (!hasAccount) {
            Spacer(Modifier.fillMaxSize().background(AppTheme.colors.hoveredMask))
            ZzzOutlineButton(
                modifier = Modifier.align(Alignment.Center),
                text = stringResource(Res.string.add_account),
                iconRes = Res.drawable.ic_link
            ) { onAddAccountClick() }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun PlayerInfo(modifier: Modifier, uiState: GameRecordState) {
    SubcomposeAsyncImage(modifier = Modifier.size(AppTheme.size.extraLargeIconSize)
        .clip(CircleShape),
        model = uiState.profileUrl,
        contentDescription = stringResource(Res.string.user_profile_image),
        error = {
            ImageNotFound()
        })
    Column(
        modifier = modifier, verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.s300)
    ) {
        OutlinedText(
            text = uiState.nickname,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = AppTheme.colors.onSurface,
            style = AppTheme.typography.labelMedium,
            borderColor = AppTheme.colors.border
        )

        OutlinedText(
            modifier = Modifier.fillMaxWidth(),
            text = uiState.uid,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = AppTheme.colors.onSurface,
            style = AppTheme.typography.labelSmall,
            borderColor = AppTheme.colors.border
        )
    }
}

@Composable
private fun PlayerEnergy(uiState: GameRecordState) {
    // Heart Rate Effect
    val infiniteTransition = rememberInfiniteTransition()
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f, targetValue = 1.4f, animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 400, easing = EaseInOut),
            repeatMode = RepeatMode.Reverse
        )
    )

    Row(
        modifier = Modifier.background(AppTheme.colors.hoveredMask, CircleShape)
            .padding(horizontal = AppTheme.spacing.s400, vertical = AppTheme.spacing.s250).clip(
                CircleShape
            ),
        horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.s300),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val isFull = uiState.energy.progress.current == uiState.energy.progress.max

        Icon(
            modifier = Modifier.scale(if (isFull) scale else 1f).size(AppTheme.size.iconSize),
            imageVector = vectorResource(Res.drawable.ic_bolt),
            contentDescription = null,
            tint = AppTheme.colors.secondary
        )
        Text(
            text = "${uiState.energy.progress.current} / ${uiState.energy.progress.max}",
            color = AppTheme.colors.onHoveredMask,
            style = AppTheme.typography.labelLarge
        )
    }
}


@Composable
private fun DailyMission(modifier: Modifier, uiState: GameRecordState) {
    Column(modifier = modifier.clip(AppTheme.shape.r400).widthIn(min = minFlowRowElementWidth)) {
        val videoStoreText = when (uiState.vhsSale.saleState) {
            "SaleStateDone" -> stringResource(Res.string.ready_to_settle)
            "SaleStateDoing" -> stringResource(Res.string.operating)
            "SaleStateNo" -> stringResource(Res.string.not_operating)
            else -> "???"
        }
        PlayerTodoItem(
            title = stringResource(Res.string.engagement_today),
            content = "${uiState.vitality.current} / ${uiState.vitality.max}"
        )
        PlayerTodoItem(
            title = stringResource(Res.string.scratch_card),
            content = if (uiState.cardSign == "CardSignNo") stringResource(Res.string.purchasable)
            else stringResource(Res.string.purchased)
        )
        PlayerTodoItem(
            title = stringResource(Res.string.video_store),
            content = videoStoreText,
            isWarning = uiState.vhsSale.saleState == "SaleStateNo"
        )
    }
}

@Composable
private fun WeeklyMission(modifier: Modifier, uiState: GameRecordState) {
    val remainOneDay = uiState.weeklyTask.refreshTime < 86400
    Column(
        modifier = modifier.clip(AppTheme.shape.r400).widthIn(min = minFlowRowElementWidth)
    ) {
        PlayerTodoItem(
            title = stringResource(Res.string.bounty_commissions),
            content = "${uiState.bountyCommission.num} / ${uiState.bountyCommission.total}",
            isWarning = remainOneDay && (uiState.bountyCommission.total > uiState.bountyCommission.num)
        )
        PlayerTodoItem(
            title = stringResource(Res.string.investigate_point),
            content = "${uiState.surveyPoints.num} / ${uiState.surveyPoints.total}",
            isWarning = remainOneDay && (uiState.surveyPoints.total > uiState.surveyPoints.num)
        )
        PlayerTodoItem(
            title = stringResource(Res.string.ridu_weekly),
            content = "${uiState.weeklyTask.curPoint} / ${uiState.weeklyTask.maxPoint}",
            isWarning = remainOneDay && (uiState.weeklyTask.curPoint < uiState.weeklyTask.maxPoint)
        )
    }
}

@Composable
fun PlayerTodoItem(
    modifier: Modifier = Modifier,
    title: String,
    content: String,
    isDone: Boolean = false,
    isWarning: Boolean = false
) {
    Row(
        modifier = modifier.fillMaxWidth().background(AppTheme.colors.surface)
            .padding(horizontal = AppTheme.spacing.s400, vertical = AppTheme.spacing.s350),
        horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.s300),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = title,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = AppTheme.colors.onSurfaceVariant,
            style = AppTheme.typography.labelSmall
        )
        Text(
            text = content,
            color = AppTheme.colors.onSurface,
            style = AppTheme.typography.labelMedium
        )
        if (isDone) {
            Icon(
                modifier = Modifier.size(AppTheme.size.iconSize),
                imageVector = vectorResource(Res.drawable.ic_check_circle),
                contentDescription = null,
                tint = AppTheme.colors.primary
            )
        }
        if (isWarning) {
            Icon(
                modifier = Modifier.size(AppTheme.size.iconSize),
                imageVector = vectorResource(Res.drawable.ic_warning),
                contentDescription = null,
                tint = AppTheme.colors.secondary
            )
        }
    }
}

@Composable
private fun SignButton(signResult: String?, onSignClick: () -> Unit) {
    Row(
        modifier = Modifier.clip(CircleShape).clickable {
            if (signResult == null) {
                onSignClick()
            }
        }.pointerHoverIcon(PointerIcon.Hand).background(AppTheme.colors.hoveredMask, CircleShape)
            .padding(horizontal = AppTheme.spacing.s400, vertical = AppTheme.spacing.s250),
        horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.s300),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(AppTheme.size.iconSize),
            imageVector = vectorResource(if (signResult == "OK") Res.drawable.ic_check_circle else Res.drawable.ic_calendar_clock),
            contentDescription = null,
            tint = if (signResult == "OK") AppTheme.colors.primary else AppTheme.colors.onHoveredMask.copy(
                alpha = if (signResult == null) 1f else 0.5f
            )
        )
        Text(
            text = if (signResult == null) stringResource(Res.string.check_in)
            else if (signResult == "OK") stringResource(Res.string.check_in_success)
            else signResult,
            color = AppTheme.colors.onHoveredMask.copy(alpha = if (signResult == null) 1f else 0.5f),
            style = AppTheme.typography.labelSmall,
            maxLines = 1
        )
    }
}
