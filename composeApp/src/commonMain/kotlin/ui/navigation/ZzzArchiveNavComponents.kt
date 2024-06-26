/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*
 * Modifications by mrfatworm, 2024
 */

package ui.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.ic_nav
import zzzarchive.composeapp.generated.resources.ic_nav_back

@Composable
fun ZzzArchiveNavigationRail(
    selectedDestination: String,
    navigationActions: ZzzArchiveNavigationActions,
    onDrawerClicked: () -> Unit = {},
) {
    NavigationRail(
        modifier = Modifier.fillMaxHeight(),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            NavigationRailItem(selected = false, onClick = onDrawerClicked, icon = {
                Icon(
                    painter = painterResource(Res.drawable.ic_nav),
                    contentDescription = "Navigation"
                )
            })
            Spacer(Modifier.height(8.dp)) // NavigationRailHeaderPadding
            Spacer(Modifier.height(4.dp)) // NavigationRailVerticalPadding
        }

        Column(
            modifier = Modifier.verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            TOP_LEVEL_DESTINATIONS_MEDIUM.forEach { destination ->
                NavigationRailItem(selected = selectedDestination == destination.route, onClick = {
                    if (selectedDestination == destination.route) {
                        navigationActions.navigationToTop(destination)
                    } else {
                        navigationActions.navigationToTopAndSave(destination)
                    }
                }, icon = {
                    Icon(
                        painter = painterResource(destination.icon),
                        contentDescription = destination.text
                    )
                }, label = {
                    Text(text = destination.text)
                })
                Spacer(Modifier.height(4.dp)) // NavigationRailVerticalPadding
            }
        }
    }
}

@Composable
fun ZzzArchiveBottomNavigationBar(
    selectedDestination: String, navigationActions: ZzzArchiveNavigationActions
) {
    NavigationBar(modifier = Modifier.fillMaxWidth()) {
        TOP_LEVEL_DESTINATIONS_COMPACT.forEach { destination ->
            NavigationBarItem(selected = selectedDestination == destination.route, onClick = {
                if (selectedDestination == destination.route) {
                    navigationActions.navigationToTop(destination)
                } else {
                    navigationActions.navigationToTopAndSave(destination)
                }
            }, icon = {
                Icon(
                    painter = painterResource(destination.icon),
                    contentDescription = destination.text
                )
            })
        }
    }
}

@Composable
fun ModalNavigationDrawerContent(
    selectedDestination: String,
    navigationActions: ZzzArchiveNavigationActions,
    onDrawerClicked: () -> Unit = {}
) {
    ModalDrawerSheet {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "ZZZ ARCHIVE",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                IconButton(onClick = onDrawerClicked) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_nav_back),
                        contentDescription = "Navigation Drawer"
                    )
                }
            }
        }

        Column(
            modifier = Modifier.verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TOP_LEVEL_DESTINATIONS_MEDIUM.forEach { destination ->
                NavigationDrawerItem(selected = selectedDestination == destination.route, label = {
                    Text(
                        text = destination.text, modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }, icon = {
                    Icon(
                        painter = painterResource(destination.icon),
                        contentDescription = destination.text
                    )
                }, colors = NavigationDrawerItemDefaults.colors(
                    unselectedContainerColor = Color.Transparent
                ), onClick = {
                    if (selectedDestination == destination.route) {
                        navigationActions.navigationToTop(destination)
                    } else {
                        navigationActions.navigationToTopAndSave(destination)
                    }
                    onDrawerClicked()
                })
                Spacer(Modifier.height(8.dp)) // NavigationRailVerticalPadding
            }
        }
    }
}

@Composable
fun PermanentNavigationDrawerContent(
    selectedDestination: String,
    navigationActions: ZzzArchiveNavigationActions,
) {
    PermanentDrawerSheet(
        modifier = Modifier.sizeIn(minWidth = 200.dp, maxWidth = 300.dp),
        drawerContainerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
    ) {
        Column(
            horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                modifier = Modifier.padding(16.dp),
                text = "ZZZ ARCHIVE",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }

        Column(
            modifier = Modifier.verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TOP_LEVEL_DESTINATIONS_MEDIUM.forEach { destination ->
                NavigationDrawerItem(selected = selectedDestination == destination.route, label = {
                    Text(
                        text = destination.text, modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }, icon = {
                    Icon(
                        painter = painterResource(destination.icon),
                        contentDescription = destination.text
                    )
                }, colors = NavigationDrawerItemDefaults.colors(
                    unselectedContainerColor = Color.Transparent
                ), onClick = { navigationActions.navigationToTopAndSave(destination) })
            }
        }
    }
}
