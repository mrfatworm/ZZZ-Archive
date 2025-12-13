/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.setting.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.style.TextAlign
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import ui.components.cards.ContentCard
import ui.theme.AppTheme
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.buymeacoffee
import zzzarchive.composeapp.generated.resources.ic_happy
import zzzarchive.composeapp.generated.resources.ko_fi
import zzzarchive.composeapp.generated.resources.sponsorship_description
import zzzarchive.composeapp.generated.resources.sponsorship_title

@Composable
fun SponsorshipCard() {
    val uriHandler = LocalUriHandler.current
    ContentCard {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.s350)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.s300)
            ) {
                Icon(
                    modifier = Modifier.size(AppTheme.size.icon),
                    imageVector = vectorResource(Res.drawable.ic_happy),
                    contentDescription = null,
                    tint = AppTheme.colors.onSurfaceContainer
                )
                Text(
                    text = stringResource(Res.string.sponsorship_title),
                    style = AppTheme.typography.titleMedium,
                    color = AppTheme.colors.onSurfaceContainer
                )
            }
            Text(
                text = stringResource(Res.string.sponsorship_description),
                style = AppTheme.typography.bodyMedium,
                color = AppTheme.colors.onSurfaceContainer
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.s350)
            ) {
                SponsorshipButton(
                    modifier = Modifier.weight(1f),
                    textRes = Res.string.buymeacoffee,
                    color = Color(0xFFFFDD00), // Buy Me a Coffee yellow
                    contentColor = Color.Black,
                    onClick = { uriHandler.openUri("https://buymeacoffee.com/mrfatworm") }
                )
                SponsorshipButton(
                    modifier = Modifier.weight(1f),
                    textRes = Res.string.ko_fi,
                    color = Color(0xFF29ABE0), // Ko-fi blue
                    contentColor = Color.White,
                    onClick = { uriHandler.openUri("https://ko-fi.com/mrfatworm") }
                )
            }
        }
    }
}

@Composable
private fun SponsorshipButton(
    modifier: Modifier = Modifier,
    textRes: StringResource,
    color: Color,
    contentColor: Color,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = color,
            contentColor = contentColor
        ),
        shape = AppTheme.shape.r400
    ) {
        Text(
            text = stringResource(textRes),
            style = AppTheme.typography.labelMedium,
            textAlign = TextAlign.Center
        )
    }
}
