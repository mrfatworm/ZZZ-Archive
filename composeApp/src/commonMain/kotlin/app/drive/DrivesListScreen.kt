/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.drive

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
import com.mrfatworm.android.zzzarchive.ui.theme.ZzzArchiveTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun DrivesListScreen(onDriveClick: (String) -> Unit = {}) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            text = "驅動光碟清單",
            textAlign = TextAlign.Center,
            fontSize = 24.sp
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Button(onClick = { onDriveClick("哈哈") }) {
                Text(text = "哈哈")
            }
        }
    }
}

@Preview
@Composable
fun PreviewDrivesListScreen() {
    ZzzArchiveTheme {
        DrivesListScreen()
    }
}