/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.drive.model

import feature.drive.data.database.DrivesListItemEntity


data class DrivesListState(
    val drivesList: List<DrivesListItemEntity> = emptyList(),
    val selectedDrive: DrivesListItemEntity? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)