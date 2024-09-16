/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package home.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import home.model.HomeState
import home.model.stubHomeState
import org.jetbrains.compose.resources.painterResource
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.compose_multiplatform

@Composable
fun HomeScreenDual(
    uiState: HomeState = stubHomeState,
    onCharacterOverviewClick: () -> Unit = {},
    onWeaponOverviewClick: () -> Unit = {},
    onEchoesOverviewClick: () -> Unit = {},
    onCharacterDetailClick: (Long) -> Unit = {},
    onWeaponDetailClick: (Long) -> Unit = {},
    onEchoDetailClick: (Long) -> Unit = {},
) {

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Surface(
                modifier = Modifier
                    .padding(16.dp)
                    .weight(1f), shape = RoundedCornerShape(16.dp)
            ) {
                Image(
                    painter = painterResource(Res.drawable.compose_multiplatform),
                    contentDescription = "cover"
                )
            }
            Text(modifier = Modifier.padding(16.dp), text = uiState.firstActivityTitle)
            HomeListSection("Characters",
                uiState.charactersList,
                onSeeAllClick = {
                    onCharacterOverviewClick()
                },
                onItemClick = { id ->
                    onCharacterDetailClick(id)
                })

            HomeListSection("Weapons",
                uiState.weaponsList,
                onSeeAllClick = {
                    onWeaponOverviewClick()
                },
                onItemClick = { id ->
                    onWeaponDetailClick(id)
                })

            HomeListSection("Artifacts",
                uiState.artifactsList,
                onSeeAllClick = {
                    onEchoesOverviewClick()
                },
                onItemClick = { id ->
                    onEchoDetailClick(id)
                })
        }
    }
}