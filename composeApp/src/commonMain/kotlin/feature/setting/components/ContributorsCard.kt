/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.setting.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import feature.setting.model.Contributor
import feature.setting.model.Contributors
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import ui.components.cards.ContentCard
import ui.theme.AppTheme
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.author
import zzzarchive.composeapp.generated.resources.banner_artists
import zzzarchive.composeapp.generated.resources.contributors
import zzzarchive.composeapp.generated.resources.data_integration
import zzzarchive.composeapp.generated.resources.developer
import zzzarchive.composeapp.generated.resources.ic_people
import zzzarchive.composeapp.generated.resources.localization
import zzzarchive.composeapp.generated.resources.special_thanks
import zzzarchive.composeapp.generated.resources.ui_ux_designers

@Composable
fun ContributorsCard(contributors: Contributors) {
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
                    modifier = Modifier.size(AppTheme.size.smallIconSize),
                    imageVector = vectorResource(Res.drawable.ic_people),
                    contentDescription = null,
                    tint = AppTheme.colors.onSurfaceVariant
                )
                Text(
                    text = contributors.contributorAmount.toString(),
                    style = AppTheme.typography.labelSmall,
                    color = AppTheme.colors.onSurfaceVariant
                )
            }
            ContributorItem(stringResource(Res.string.author), contributors.author)
            ContributorItem(stringResource(Res.string.developer), contributors.developer)
            ContributorItem(stringResource(Res.string.ui_ux_designers), contributors.uiUxDesigner)
            ContributorItem(stringResource(Res.string.localization), contributors.translation)
            ContributorItem(
                stringResource(Res.string.data_integration), contributors.dataIntegration
            )
            ContributorItem(stringResource(Res.string.banner_artists), contributors.bannerArtists)
            ContributorItem(stringResource(Res.string.special_thanks), contributors.specialThanks)
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
            SelectionContainer {
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
}