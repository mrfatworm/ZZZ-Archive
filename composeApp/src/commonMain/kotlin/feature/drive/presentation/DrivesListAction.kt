/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.drive.presentation

sealed interface DrivesListAction {
    data class ClickDriveDetail(val id: Int) : DrivesListAction
    data object DismissDriveDetail : DrivesListAction
    data object ClickBack : DrivesListAction
}