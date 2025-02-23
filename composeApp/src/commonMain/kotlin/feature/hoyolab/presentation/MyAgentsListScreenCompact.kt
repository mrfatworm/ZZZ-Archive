/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import feature.hoyolab.components.MyAgentsListCard
import feature.hoyolab.model.MyAgentsListState
import org.jetbrains.compose.resources.stringResource
import ui.components.TopBarScaffold
import ui.theme.AppTheme
import ui.utils.contentPaddingInScaffold
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.agents

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAgentsListScreenCompact(
    uiState: MyAgentsListState,
    onAction: (MyAgentsListAction) -> Unit
) {
    Scaffold(containerColor = AppTheme.colors.surface, topBar = {
        TopBarScaffold(
            title = stringResource(Res.string.agents),
            onBackClick = {
                onAction(MyAgentsListAction.ClickBack)
            })
    }) { scaffoldPadding ->
        Column(
            modifier = Modifier.contentPaddingInScaffold(scaffoldPadding)
        ) {
            MyAgentsListCard(
                modifier = Modifier.weight(1f),
                uiState = uiState,
                onAgentClick = {
                    onAction(MyAgentsListAction.ClickAgent(it))
                }
            )
        }
    }
}