/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.function

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.theme.ZzzArchiveTheme

@Composable
fun FunctionScreenDual() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Function Dual")
    }
}


@Preview
@Composable
private fun PreviewFunctionScreenDual() {
    ZzzArchiveTheme {
        FunctionScreenDual()
    }
}