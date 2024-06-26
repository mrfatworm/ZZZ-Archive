/*
 *  Copyright 2024, mrfatworm
 *  License: Apache-2.0
 */

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.compose_multiplatform

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
@Preview
fun ZzzArchiveApp() {
    MaterialTheme {
        /**
         * Issue: [WindowWidthSizeClass will soon be deprecated](https://github.com/chrisbanes/material3-windowsizeclass-multiplatform/issues/100)
         */
        val windowSizeClass = calculateWindowSizeClass()
        var buttonText by remember { mutableStateOf("") }

        when (windowSizeClass.widthSizeClass) {
            WindowWidthSizeClass.Compact -> {
                buttonText = "Compact"
            }

            WindowWidthSizeClass.Medium -> {
                buttonText = "Medium"
            }

            WindowWidthSizeClass.Expanded -> {
                buttonText = "Expanded"
            }

            else -> {
                buttonText = "Else"
            }
        }
        var showContent by remember { mutableStateOf(false) }
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = { showContent = !showContent }) {
                Text(buttonText)
            }
            AnimatedVisibility(showContent) {
                val greeting = remember { Greeting().greet() }
                Column(
                    Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(painterResource(Res.drawable.compose_multiplatform), null)
                    Text("Compose: $greeting")
                }
            }
        }
    }
}