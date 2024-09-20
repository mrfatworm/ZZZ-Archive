/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package ui.navigation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mrfatworm.android.zzzarchive.ui.theme.AppTheme
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import ui.navigation.NavActions
import ui.navigation.TOP_LEVEL_DESTINATIONS_MEDIUM
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.dark_theme
import zzzarchive.composeapp.generated.resources.ic_moon
import zzzarchive.composeapp.generated.resources.ic_nav
import zzzarchive.composeapp.generated.resources.ic_sun
import zzzarchive.composeapp.generated.resources.light_theme
import zzzarchive.composeapp.generated.resources.navigation_drawer

@Composable
fun ZzzArchiveNavigationRail(
    selectedDestination: String,
    navigationActions: NavActions,
    onDrawerClicked: () -> Unit = {},
) {
    NavigationRail(
        modifier = Modifier
            .fillMaxHeight()
            .border(3.dp, AppTheme.colors.border, CircleShape)
            .background(AppTheme.colors.surfaceContainer, CircleShape),
        containerColor = Color.Transparent,
        header = {
            NavigationRailItem(selected = false, onClick = onDrawerClicked, icon = {
                Icon(
                    imageVector = vectorResource(Res.drawable.ic_nav),
                    contentDescription = stringResource(Res.string.navigation_drawer),
                    tint = AppTheme.colors.onSurfaceVariant
                )
            })
        }
    ) {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            TOP_LEVEL_DESTINATIONS_MEDIUM.forEach { destination ->
                val isSelected = selectedDestination == destination.route
                NavigationRailItem(
                    selected = isSelected, onClick = {
                        if (isSelected) {
                            navigationActions.navigationToTop(destination)
                        } else {
                            navigationActions.navigationToTopAndSave(destination)
                        }
                    }, icon = {
                        Icon(
                            imageVector = vectorResource(destination.iconRes),
                            contentDescription = stringResource(destination.textRes)
                        )
                    }, label = {
                        Text(
                            text = stringResource(destination.textRes),
                            style = if (isSelected) AppTheme.typography.labelMedium else AppTheme.typography.labelSmall
                        )
                    }, colors = navigationRailItemColors()
                )
                Spacer(Modifier.height(4.dp)) // NavigationRailVerticalPadding
            }
        }
        Spacer(
            modifier = Modifier
                .weight(1f)
                .heightIn(min = 16.dp)
        )

        var isDark by AppTheme.isDark
        NavigationRailItem(selected = false, onClick = {
            isDark = !isDark
        }, icon = {
            Icon(
                imageVector = vectorResource(if (isDark) Res.drawable.ic_sun else Res.drawable.ic_moon),
                contentDescription = stringResource(if (isDark) Res.string.light_theme else Res.string.dark_theme),
                tint = AppTheme.colors.onSurfaceVariant
            )
        })

    }
}

@Composable
private fun navigationRailItemColors() = NavigationRailItemDefaults.colors(
    selectedIconColor = AppTheme.colors.onPrimaryContainer,
    selectedTextColor = AppTheme.colors.onSurfaceContainer,
    indicatorColor = AppTheme.colors.primaryContainer,
    unselectedIconColor = AppTheme.colors.onSurfaceVariant,
    unselectedTextColor = AppTheme.colors.onSurfaceVariant
)
