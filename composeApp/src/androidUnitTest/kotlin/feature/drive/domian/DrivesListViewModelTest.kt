/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.drive.domian


import MainDispatcherRule
import feature.drive.data.FakeDriveRepository
import feature.drive.domain.DrivesListViewModel
import feature.drive.model.stubDrivesListResponse
import org.junit.Rule
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class DrivesListViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val driveRepository = FakeDriveRepository()
    private lateinit var viewModel: DrivesListViewModel

    @BeforeTest
    fun setup() {
        viewModel = DrivesListViewModel(driveRepository)
    }

    @Test
    fun `Init Data Success`() {
        val state = viewModel.uiState.value
        assertEquals(state.drivesList, stubDrivesListResponse.getDrivesNewToOld())
    }

    @Test
    fun `Select Drive`() {
        viewModel.onDriveClick(1)
        val state = viewModel.uiState.value
        assertEquals(state.selectedDrive?.id, 1)
    }

    @Test
    fun `Dismiss Detail`() {
        viewModel.onDriveClick(1)
        viewModel.onDetailDismiss()
        val state = viewModel.uiState.value
        assertEquals(state.selectedDrive, null)

    }
}