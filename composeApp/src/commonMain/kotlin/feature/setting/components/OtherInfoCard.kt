/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.setting.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import ui.components.ZzzTag
import ui.components.cards.ContentCard
import ui.theme.AppTheme
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.feedback
import zzzarchive.composeapp.generated.resources.ic_arrow_next_ios
import zzzarchive.composeapp.generated.resources.ic_figma
import zzzarchive.composeapp.generated.resources.ic_github
import zzzarchive.composeapp.generated.resources.open_source
import zzzarchive.composeapp.generated.resources.privacy_policy

@Composable
fun OtherInfoCard(
    onFeedbackClick: () -> Unit
) {
    ContentCard(hasDefaultPadding = false) {
        FeedbackItem(onClick = onFeedbackClick)
        PrivacyPolicyItem()
        OpenSourceItem()
    }
}

@Composable
private fun FeedbackItem(onClick: () -> Unit) {
    SettingItem(title = stringResource(Res.string.feedback), content = {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                modifier = Modifier.size(12.dp),
                imageVector = vectorResource(Res.drawable.ic_arrow_next_ios),
                contentDescription = null,
                tint = AppTheme.colors.onSurfaceVariant
            )
        }

    }, onClick = onClick)
}

@Composable
private fun PrivacyPolicyItem() {
    val urlHandler = LocalUriHandler.current
    SettingItem(title = stringResource(Res.string.privacy_policy), content = {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                modifier = Modifier.size(12.dp),
                imageVector = vectorResource(Res.drawable.ic_arrow_next_ios),
                contentDescription = null,
                tint = AppTheme.colors.onSurfaceVariant
            )
        }
    }, onClick = {
        urlHandler.openUri("https://hackmd.io/p3BDitRVTF2GQpawLRylRA")
    })
}

@Composable
private fun OpenSourceItem() {
    var isExpanded by remember { mutableStateOf(false) }
    val urlHandler = LocalUriHandler.current
    Column {
        SettingItem(title = stringResource(Res.string.open_source), content = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = vectorResource(Res.drawable.ic_figma),
                    contentDescription = "Figma",
                    tint = Color.Unspecified
                )
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = vectorResource(Res.drawable.ic_github),
                    contentDescription = "GitHub",
                    tint = AppTheme.colors.onSurfaceContainer
                )
            }

        }, onClick = {
            isExpanded = !isExpanded
        })
        AnimatedVisibility(visible = isExpanded) {
            Row(
                modifier = Modifier.padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OpenSourceItem(modifier = Modifier.weight(1f),
                    iconRes = Res.drawable.ic_figma,
                    title = "Figma",
                    label = "CC-BY-SA 4.0",
                    isTintIcon = false,
                    onClick = {
                        urlHandler.openUri("https://www.figma.com/@mrfatworm")
                    })
                OpenSourceItem(modifier = Modifier.weight(1f),
                    iconRes = Res.drawable.ic_github,
                    title = "GitHub",
                    label = "MIT",
                    onClick = {
                        urlHandler.openUri("https://github.com/mrfatworm/ZZZ-Archive")
                    })
            }
        }

    }
}

@Composable
fun OpenSourceItem(
    modifier: Modifier,
    iconRes: DrawableResource,
    title: String,
    label: String,
    isTintIcon: Boolean = true,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier.clip(RoundedCornerShape(AppTheme.radius.contentCard)).clickable {
            onClick()
        }.border(
            AppTheme.dimens.borderWidth,
            AppTheme.colors.buttonBorder,
            RoundedCornerShape(AppTheme.radius.contentCard)
        ).padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Icon(
            modifier = Modifier.size(48.dp),
            imageVector = vectorResource(iconRes),
            contentDescription = null,
            tint = if (isTintIcon) AppTheme.colors.onSurfaceContainer else Color.Unspecified
        )
        Text(
            text = title,
            style = AppTheme.typography.titleSmall,
            color = AppTheme.colors.onSurfaceContainer
        )
        ZzzTag(text = label)
    }
}