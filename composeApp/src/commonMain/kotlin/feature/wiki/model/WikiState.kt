/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.wiki.model

import feature.drive.model.DriveListItemResponse
import feature.wengine.model.WEngineListItem

data class WikiState(
    val wEnginesList: List<WEngineListItem> = emptyList(),
    val drivesList: List<DriveListItemResponse> = emptyList()
)

