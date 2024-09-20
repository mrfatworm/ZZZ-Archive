/*
 *  Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 *  License: CC BY-SA 4.0
 */

package setting

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ui.utils.ContentType

@Composable
fun SettingScreen(
    contentType: ContentType,
    onFeedbackClicked: () -> Unit
) {
    if (contentType == ContentType.SINGLE) {
        SettingScreenSingle(onFeedbackClicked)
    } else {
        SettingScreenDual()
    }
}

@Composable
fun SettingScreenSingle(onFeedbackClicked: () -> Unit
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                16.dp, alignment = Alignment.CenterVertically
            )
        ) {
            Text(text = "Setting")
            Button(onClick = { onFeedbackClicked() }) {
                Text(text = "Feedback")
            }
        }
    }
}

@Composable
fun SettingScreenDual() {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                16.dp, alignment = Alignment.CenterVertically
            )
        ) {
            Text(text = "Setting")
        }
    }
}