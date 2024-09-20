/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
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
import ui.utils.NavigationType
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.compose_multiplatform

@Composable
fun HomeScreenSingle(
    uiState: HomeState = stubHomeState,
    navigationType: NavigationType = NavigationType.BOTTOM_NAVIGATION,
    onAgentsOverviewClick: () -> Unit = {},
    onWEnginesOverviewClick: () -> Unit = {},
    onDriversOverviewClick: () -> Unit = {},
    onAgentDetailClick: (Long) -> Unit = {},
    onWEngineDetailClick: (Long) -> Unit = {},
    onDriverDetailClick: (Long) -> Unit = {},
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
            Text(modifier = Modifier.padding(16.dp), text = uiState.activityImageUrl)
            HomeListSection(
                "Agents",
                uiState.agentsList,
                onSeeAllClick = {
                    onAgentsOverviewClick()
                },
                onItemClick = { id ->
                    onAgentDetailClick(id)
                })

            HomeListSection(
                "W-Engines",
                uiState.wEnginesList,
                onSeeAllClick = {
                    onWEnginesOverviewClick()
                },
                onItemClick = { id ->
                    onWEngineDetailClick(id)
                })

            HomeListSection(
                "Drivers",
                uiState.driversList,
                onSeeAllClick = {
                    onDriversOverviewClick()
                },
                onItemClick = { id ->
                    onDriverDetailClick(id)
                })
        }
    }
}