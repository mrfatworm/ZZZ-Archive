/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.wengine

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
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.component.ZzzOutlineButton
import ui.theme.ZzzArchiveTheme
import zzzarchive.composeapp.generated.resources.Res
import zzzarchive.composeapp.generated.resources.w_engines

@Composable
fun WEnginesListScreen(onWEngineClick: (String) -> Unit = {}) {
    Text(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        text = stringResource(Res.string.w_engines),
        textAlign = TextAlign.Center,
        fontSize = 24.sp
    )
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        ZzzOutlineButton(text = "硫磺石", onClick = { onWEngineClick("硫磺石") })
    }
}

@Preview
@Composable
fun PreviewWEnginesListScreen() {
    ZzzArchiveTheme {
        WEnginesListScreen()
    }
}