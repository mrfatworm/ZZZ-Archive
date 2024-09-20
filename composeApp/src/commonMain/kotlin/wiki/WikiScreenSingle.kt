/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package wiki

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mrfatworm.android.zzzarchive.ui.theme.ZzzArchiveTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun WikiScreenSingle(
    onAgentsOverviewClick: () -> Unit = {},
    onWEnginesOverviewClick: () -> Unit = {},
    onDriversOverviewClick: () -> Unit = {},
    onAgentDetailClick: (Long) -> Unit = {},
    onWEngineDetailClick: (Long) -> Unit = {},
    onDriverDetailClick: (Long) -> Unit = {},
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Wiki Single")
    }
}


@Preview
@Composable
fun PreviewWikiScreenSingle() {
    ZzzArchiveTheme {
        WikiScreenSingle()
    }
}