/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.wiki

import androidx.compose.runtime.Composable
import ui.utils.ContentType

@Composable
fun WikiScreen(
    contentType: ContentType,
    onAgentOverviewClick: () -> Unit,
    onWEngineOverviewClick: () -> Unit,
    onDrivesOverviewClick: () -> Unit,
    onAgentDetailClick: (Long) -> Unit = {},
    onWEngineDetailClick: (Long) -> Unit = {},
    onDriveDetailClick: (Long) -> Unit = {},
) {
    if (contentType == ContentType.SINGLE) {
        WikiScreenSingle(
            onAgentsOverviewClick = onAgentOverviewClick,
            onWEnginesOverviewClick = onWEngineOverviewClick,
            onDrivesOverviewClick = onDrivesOverviewClick,
            onAgentDetailClick = onAgentDetailClick,
            onWEngineDetailClick = onWEngineDetailClick,
            onDriveDetailClick = onDriveDetailClick,
        )
    } else {
        WikiScreenDual(
            onAgentsOverviewClick = onAgentOverviewClick,
            onWEnginesOverviewClick = onWEngineOverviewClick,
            onDrivesOverviewClick = onDrivesOverviewClick,
            onAgentDetailClick = onAgentDetailClick,
            onWEngineDetailClick = onWEngineDetailClick,
            onDriveDetailClick = onDriveDetailClick,
        )
    }
}