/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package wiki

import androidx.compose.runtime.Composable
import ui.utils.ContentType

@Composable
fun WikiScreen(
    contentType: ContentType,
    onAgentOverviewClick: () -> Unit,
    onWEngineOverviewClick: () -> Unit,
    onDriversOverviewClick: () -> Unit,
    onAgentDetailClick: (Long) -> Unit = {},
    onWEngineDetailClick: (Long) -> Unit = {},
    onDriverDetailClick: (Long) -> Unit = {},
) {
    if (contentType == ContentType.SINGLE) {
        WikiScreenSingle(
            onAgentsOverviewClick = onAgentOverviewClick,
            onWEnginesOverviewClick = onWEngineOverviewClick,
            onDriversOverviewClick = onDriversOverviewClick,
            onAgentDetailClick = onAgentDetailClick,
            onWEngineDetailClick = onWEngineDetailClick,
            onDriverDetailClick = onDriverDetailClick,
        )
    } else {
        WikiScreenDual(
            onAgentsOverviewClick = onAgentOverviewClick,
            onWEnginesOverviewClick = onWEngineOverviewClick,
            onDriversOverviewClick = onDriversOverviewClick,
            onAgentDetailClick = onAgentDetailClick,
            onWEngineDetailClick = onWEngineDetailClick,
            onDriverDetailClick = onDriverDetailClick,
        )
    }
}