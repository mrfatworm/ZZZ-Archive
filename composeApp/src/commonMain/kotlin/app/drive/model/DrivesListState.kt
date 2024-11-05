/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package app.drive.model


data class DrivesListState(
    val drivesList: List<DriveListItem> = emptyList(),
    val selectedDrive: DriveListItem? = null
)