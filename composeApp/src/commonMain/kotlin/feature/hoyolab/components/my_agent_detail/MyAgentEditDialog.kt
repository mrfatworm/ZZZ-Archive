/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.hoyolab.components.my_agent_detail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import feature.hoyolab.model.my_agent_detail.MyAgentDetailState
import feature.hoyolab.presentation.MyAgentDetailAction
import feature.setting.components.SettingSwitchItem
import org.jetbrains.compose.resources.stringResource
import ui.components.ZzzTextFiled
import ui.components.buttons.ZzzIconButton
import ui.components.buttons.ZzzPrimaryButton
import ui.components.dialogs.BasicDialog
import ui.theme.AppTheme
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.apply
import zzzarchive.composeapp.generated.resources.author_name_optional
import zzzarchive.composeapp.generated.resources.background_blur
import zzzarchive.composeapp.generated.resources.close
import zzzarchive.composeapp.generated.resources.custom_image
import zzzarchive.composeapp.generated.resources.display_uid
import zzzarchive.composeapp.generated.resources.ic_close
import zzzarchive.composeapp.generated.resources.image_url
import zzzarchive.composeapp.generated.resources.setting

@Composable
fun MyAgentEditDialog(
    uiState: MyAgentDetailState, onAction: (MyAgentDetailAction) -> Unit, onDismiss: () -> Unit
) {
    BasicDialog(onDismissRequest = onDismiss) {
        TopBar(onDismiss)
        Content(uiState, onApply = { showUid, isCustom, imageUrl, author, hasBlurBackground ->
            onAction(
                MyAgentDetailAction.ConfirmEditImage(
                    showUid,
                    isCustom,
                    imageUrl,
                    author,
                    hasBlurBackground
                )
            )
            onDismiss()
        })
    }
}

@Composable
private fun TopBar(onDismiss: () -> Unit) {
    Box(
        Modifier.fillMaxWidth()
            .padding(horizontal = AppTheme.spacing.s400, vertical = AppTheme.spacing.s350)
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = stringResource(Res.string.setting),
            color = AppTheme.colors.onSurfaceVariant,
            style = AppTheme.typography.titleMedium
        )
        ZzzIconButton(
            modifier = Modifier.align(Alignment.CenterEnd),
            iconRes = Res.drawable.ic_close,
            contentDescriptionRes = Res.string.close
        ) {
            onDismiss()
        }
    }
}

@Composable
private fun Content(
    uiState: MyAgentDetailState, onApply: (Boolean, Boolean, String, String, Boolean) -> Unit
) {
    var showUid by remember { mutableStateOf(uiState.showUid) }
    var isCustomImage by remember { mutableStateOf(uiState.isCustomImage) }
    var hasBlurBackground by remember { mutableStateOf(uiState.hasBlurBackground) }
    var customImageUrl by remember { mutableStateOf(uiState.customImgUrl) }
    var customImageAuthor by remember { mutableStateOf(uiState.customImgAuthor) }
    Column {
        SettingSwitchItem(stringResource(Res.string.display_uid), showUid) {
            showUid = it
        }
        SettingSwitchItem(stringResource(Res.string.custom_image), isCustomImage) {
            isCustomImage = it
        }
        AnimatedVisibility(visible = isCustomImage) {
            Column {
                ZzzTextFiled(
                    modifier = Modifier.fillMaxWidth()
                        .padding(
                            horizontal = AppTheme.spacing.s400,
                            vertical = AppTheme.spacing.s200
                        ),
                    hint = stringResource(Res.string.image_url),
                    value = customImageUrl,
                    onValueChange = { customImageUrl = it }
                )
                ZzzTextFiled(
                    modifier = Modifier.fillMaxWidth()
                        .padding(
                            horizontal = AppTheme.spacing.s400,
                            vertical = AppTheme.spacing.s200
                        ),
                    hint = stringResource(Res.string.author_name_optional),
                    value = customImageAuthor,
                    onValueChange = { customImageAuthor = it }
                )
                SettingSwitchItem(stringResource(Res.string.background_blur), hasBlurBackground) {
                    hasBlurBackground = it
                }
            }
        }
        ZzzPrimaryButton(
            modifier = Modifier.fillMaxWidth().padding(AppTheme.spacing.s400),
            text = stringResource(Res.string.apply)
        ) {
            onApply(showUid, isCustomImage, customImageUrl, customImageAuthor, hasBlurBackground)
        }
    }
}