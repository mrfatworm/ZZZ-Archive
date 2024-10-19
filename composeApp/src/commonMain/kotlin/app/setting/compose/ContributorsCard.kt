/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.setting.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import app.setting.model.Contributor
import app.setting.model.SettingState
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import ui.component.ContentCard
import ui.theme.AppTheme
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.author
import zzzarchive.composeapp.generated.resources.banner_artists
import zzzarchive.composeapp.generated.resources.contributors
import zzzarchive.composeapp.generated.resources.data_integration
import zzzarchive.composeapp.generated.resources.developer
import zzzarchive.composeapp.generated.resources.ic_people
import zzzarchive.composeapp.generated.resources.special_thanks
import zzzarchive.composeapp.generated.resources.translation
import zzzarchive.composeapp.generated.resources.ui_ux_designers

@Composable
fun ContributorsCard(settingState: SettingState) {
    ContentCard {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(Res.string.contributors),
                textAlign = TextAlign.Center,
                style = AppTheme.typography.titleMedium,
                color = AppTheme.colors.onSurfaceVariant
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(12.dp),
                    imageVector = vectorResource(Res.drawable.ic_people),
                    contentDescription = null,
                    tint = AppTheme.colors.onSurfaceVariant
                )
                Text(
                    text = settingState.contributorAmount.toString(),
                    style = AppTheme.typography.labelSmall,
                    color = AppTheme.colors.onSurfaceVariant
                )
            }
            ContributorItem(stringResource(Res.string.author), settingState.author)
            ContributorItem(stringResource(Res.string.developer), settingState.developer)
            ContributorItem(stringResource(Res.string.ui_ux_designers), settingState.uiUxDesigner)
            ContributorItem(stringResource(Res.string.translation), settingState.translation)
            ContributorItem(
                stringResource(Res.string.data_integration), settingState.dataIntegration
            )
            ContributorItem(stringResource(Res.string.banner_artists), settingState.bannerArtists)
            ContributorItem(stringResource(Res.string.special_thanks), settingState.specialThanks)
        }
    }
}

@Composable
fun ContributorItem(title: String, contributorList: List<Contributor>) {
    Column(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
        Text(
            text = title,
            style = AppTheme.typography.titleSmall,
            color = AppTheme.colors.onSurfaceVariant
        )
        contributorList.forEach { contributor ->
            Row(
                modifier = Modifier.padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = contributor.name,
                    style = AppTheme.typography.labelMedium,
                    color = AppTheme.colors.onSurfaceContainer
                )
                Text(
                    text = contributor.description,
                    style = AppTheme.typography.labelSmall,
                    color = AppTheme.colors.onSurfaceVariant
                )
            }
        }
    }
}