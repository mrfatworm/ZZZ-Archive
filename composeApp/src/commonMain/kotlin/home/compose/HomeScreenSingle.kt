/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: Apache-2.0
 */

package home.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
fun HomeScreenSingle(
    viewModelText: String,
    homeState: HomeState = stubHomeState,
    onCharacterOverviewClick: () -> Unit = {},
    onWeaponOverviewClick: () -> Unit = {},
    onEchoesOverviewClick: () -> Unit = {},
    onCharacterDetailClick: (Long) -> Unit = {},
    onWeaponDetailClick: (Long) -> Unit = {},
    onEchoDetailClick: (Long) -> Unit = {},
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Surface(modifier = Modifier.padding(16.dp), shape = RoundedCornerShape(16.dp)) {
                Image(
                    modifier = Modifier.fillMaxWidth(),
                    painter = painterResource(Res.drawable.compose_multiplatform),
                    contentDescription = "cover"
                )
            }
            Text(modifier = Modifier.padding(16.dp), text = viewModelText)
            HomeListSection("Characters",
                homeState.charactersList,
                onSeeAllClick = {
                    onCharacterOverviewClick()
                },
                onItemClick = { id ->
                    onCharacterDetailClick(id)
                })

            HomeListSection("Weapons",
                homeState.weaponsList,
                onSeeAllClick = {
                    onWeaponOverviewClick()
                },
                onItemClick = { id ->
                    onWeaponDetailClick(id)
                })

            HomeListSection("Artifacts",
                homeState.artifactsList,
                onSeeAllClick = {
                    onEchoesOverviewClick()
                },
                onItemClick = { id ->
                    onEchoDetailClick(id)
                })
        }
    }
}