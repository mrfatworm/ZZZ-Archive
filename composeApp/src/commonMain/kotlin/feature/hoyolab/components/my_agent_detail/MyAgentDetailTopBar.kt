/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.hoyolab.components.my_agent_detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import feature.hoyolab.model.my_agent_detail.MyAgentDetailState
import feature.hoyolab.presentation.MyAgentDetailAction
import ui.components.TopBarRound
import ui.components.buttons.ZzzIconButton
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.ic_edit

@Composable
fun MyAgentDetailTopBar(
    uiState: MyAgentDetailState,
    onAction: (MyAgentDetailAction) -> Unit
) {
    val openEditDialog = remember { mutableStateOf(false) }
    TopBarRound(
        if (uiState.showUid) "UID: ${uiState.uid}" else uiState.agentDetail.name,
        onBackClick = {
            onAction(MyAgentDetailAction.ClickBack)
        },
        actions = {
            ZzzIconButton(iconRes = Res.drawable.ic_edit) {
                openEditDialog.value = true
            }
        })

    when {
        openEditDialog.value -> {
            MyAgentEditDialog(uiState, onAction) {
                openEditDialog.value = false
            }
        }
    }
}