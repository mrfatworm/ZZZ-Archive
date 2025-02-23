/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import feature.hoyolab.components.MyAgentsListCard
import feature.hoyolab.model.MyAgentsListState
import org.jetbrains.compose.resources.stringResource
import ui.components.TopBarRound
import ui.utils.contentGap
import ui.utils.horizontalSafePadding
import ui.utils.verticalSafePadding
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.my_agent

@Composable
fun MyAgentsListScreenMedium(
    uiState: MyAgentsListState,
    onAction: (MyAgentsListAction) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontalSafePadding())
            .padding(verticalSafePadding()),
        verticalArrangement = Arrangement.spacedBy(contentGap())
    ) {
        TopBarRound(title = stringResource(Res.string.my_agent), onBackClick = {
            onAction(MyAgentsListAction.ClickBack)
        })

        MyAgentsListCard(
            modifier = Modifier.weight(1f),
            uiState = uiState,
            onAgentClick = {
                onAction(MyAgentsListAction.ClickAgent(it))
            }
        )
    }
}