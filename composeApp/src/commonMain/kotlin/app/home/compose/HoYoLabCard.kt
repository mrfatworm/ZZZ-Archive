/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.home.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import ui.component.ContentCard
import ui.component.ImageNotFound
import ui.theme.AppTheme
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.bounty_commissions
import zzzarchive.composeapp.generated.resources.check_in
import zzzarchive.composeapp.generated.resources.engagement_today
import zzzarchive.composeapp.generated.resources.ic_bolt
import zzzarchive.composeapp.generated.resources.ic_calendar_clock
import zzzarchive.composeapp.generated.resources.ic_check_circle
import zzzarchive.composeapp.generated.resources.ic_dot
import zzzarchive.composeapp.generated.resources.ic_warning
import zzzarchive.composeapp.generated.resources.incomplete
import zzzarchive.composeapp.generated.resources.investigate_point
import zzzarchive.composeapp.generated.resources.open
import zzzarchive.composeapp.generated.resources.scratch_card
import zzzarchive.composeapp.generated.resources.video_store

@Composable
fun HoYoLabCard() {
    BoxWithConstraints {
        if (maxWidth > 450.dp) {
            HoYoLabHorizontalCard()
        } else {
            HoYoLabVerticalCard()
        }
    }
}

@Composable
fun HoYoLabHorizontalCard() {
    ContentCard(modifier = Modifier.fillMaxWidth()) {
        Header()
        Spacer(Modifier.size(16.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            DailyMission(Modifier.weight(1f))
            WeeklyMission(Modifier.weight(1f))
        }
    }
}

@Composable
fun HoYoLabVerticalCard() {

    ContentCard(modifier = Modifier.fillMaxWidth()) {
        Header()
        Spacer(Modifier.size(16.dp))
        DailyMission(Modifier.fillMaxWidth())
        Spacer(Modifier.size(8.dp))
        WeeklyMission(Modifier.fillMaxWidth())
    }
}

@Composable
private fun Header() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        PlayerInfo(Modifier.weight(1f))
        PlayerEnergy()
    }
}

@Composable
private fun PlayerInfo(modifier: Modifier) {
    Box(
        modifier = Modifier.size(48.dp)
            .border(AppTheme.dimens.borderWidth, AppTheme.colors.border, CircleShape)
            .clip(CircleShape)
    ) {
        ImageNotFound()
    }
    Column(
        modifier = modifier, verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "mrfatworm",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = AppTheme.colors.onSurfaceContainer,
            style = AppTheme.typography.labelMedium
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Asia",
                color = AppTheme.colors.onSurfaceVariant,
                style = AppTheme.typography.labelSmall
            )
            Icon(
                modifier = Modifier.size(16.dp),
                imageVector = vectorResource(Res.drawable.ic_dot),
                contentDescription = null,
                tint = AppTheme.colors.onSurfaceVariant
            )
            Text(
                text = "UID",
                color = AppTheme.colors.onSurfaceVariant,
                style = AppTheme.typography.labelSmall
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "1300051361",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = AppTheme.colors.onSurfaceVariant,
                style = AppTheme.typography.labelSmall
            )
        }
    }
}

@Composable
private fun PlayerEnergy() {
    Row(
        modifier = Modifier.background(AppTheme.colors.surface, CircleShape)
            .padding(horizontal = 16.dp, vertical = 8.dp).clip(
                CircleShape
            ),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = vectorResource(Res.drawable.ic_bolt),
            contentDescription = null,
            tint = AppTheme.colors.primary
        )
        Text(
            text = "173/240",
            color = AppTheme.colors.onSurface,
            style = AppTheme.typography.labelLarge
        )
    }
}


@Composable
private fun DailyMission(modifier: Modifier) {
    Column(modifier = modifier.clip(RoundedCornerShape(16.dp))) {
        PlayerTodoItem(
            title = stringResource(Res.string.engagement_today), content = "0/400"
        )
        PlayerTodoItem(
            title = stringResource(Res.string.scratch_card),
            content = stringResource(Res.string.incomplete)
        )
        PlayerTodoItem(
            title = stringResource(Res.string.video_store),
            content = stringResource(Res.string.open)
        )
    }
}

@Composable
private fun WeeklyMission(modifier: Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Column(modifier = Modifier.clip(RoundedCornerShape(16.dp))) {
            PlayerTodoItem(
                title = stringResource(Res.string.bounty_commissions), content = "0/4"
            )
            PlayerTodoItem(
                title = stringResource(Res.string.investigate_point), content = "0/8000"
            )
        }
        CheckInButton()
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
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
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
                modifier = Modifier.size(16.dp),
                imageVector = vectorResource(Res.drawable.ic_check_circle),
                contentDescription = null,
                tint = AppTheme.colors.primary
            )
        }
        if (isWarning) {
            Icon(
                modifier = Modifier.size(16.dp),
                imageVector = vectorResource(Res.drawable.ic_warning),
                contentDescription = null,
                tint = AppTheme.colors.secondary
            )
        }
    }
}

@Composable
private fun CheckInButton() {
    Row(
        modifier = Modifier.background(AppTheme.colors.surface, CircleShape)
            .padding(horizontal = 16.dp, vertical = 8.dp).clip(
                CircleShape
            ),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(18.dp),
            imageVector = vectorResource(Res.drawable.ic_calendar_clock),
            contentDescription = null,
            tint = AppTheme.colors.onSurfaceVariant
        )
        Text(
            text = stringResource(Res.string.check_in),
            color = AppTheme.colors.onSurface,
            style = AppTheme.typography.labelSmall
        )
    }
}
