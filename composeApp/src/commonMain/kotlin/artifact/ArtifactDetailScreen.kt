/*
 *  Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 *  License: CC BY-SA 4.0
 */

package artifact

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ArtifactDetailScreen(onCharacterClick: (String) -> Unit = {}) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            text = "DVD",
            textAlign = TextAlign.Center,
            fontSize = 24.sp
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Button(onClick = { onCharacterClick("ZZZ Boy") }) {
                Text(text = "Suggest：ZZZ Boy")
            }
        }
    }
}