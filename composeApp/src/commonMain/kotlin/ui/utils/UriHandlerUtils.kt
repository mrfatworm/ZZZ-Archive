/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package ui.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler
import org.jetbrains.compose.resources.stringResource
import ui.theme.LocalSnackbarController
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.unable_to_open_link

@Composable
fun rememberSafeUriHandler(
    handler: UriHandler = LocalUriHandler.current,
    snackbarController: SnackbarController = LocalSnackbarController.current
): UriHandler {
    val errorMessage = stringResource(Res.string.unable_to_open_link)
    return remember(handler, snackbarController, errorMessage) {
        SafeUriHandler(handler, snackbarController, errorMessage)
    }
}

class SafeUriHandler(
    private val handler: UriHandler,
    private val snackbarController: SnackbarController,
    private val errorMessageTemplate: String
) : UriHandler {
    override fun openUri(uri: String) {
        try {
            handler.openUri(uri)
        } catch (_: Exception) {
            snackbarController.showSnackbar(errorMessageTemplate.replace("%s", uri))
        }
    }
}

fun UriHandler.openUriSafe(uri: String) {
    this.openUri(uri)
}
