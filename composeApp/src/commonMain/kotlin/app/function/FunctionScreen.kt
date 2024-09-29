/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.function

import androidx.compose.runtime.Composable
import ui.utils.ContentType

@Composable
fun FunctionScreen(
    contentType: ContentType
) {
    if (contentType == ContentType.Single) {
        FunctionScreenSingle()
    } else {
        FunctionScreenDual()
    }
}