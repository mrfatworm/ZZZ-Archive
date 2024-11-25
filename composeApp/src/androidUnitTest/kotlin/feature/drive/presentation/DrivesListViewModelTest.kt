/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.drive.presentation


import MainDispatcherRule
import feature.drive.domain.DrivesListUseCase
import feature.drive.model.stubDrivesListResponse
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Rule
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class DrivesListViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val drivesListUseCase = mockk<DrivesListUseCase>()
    private lateinit var viewModel: DrivesListViewModel

    @BeforeTest
    fun setup() {
        coEvery { drivesListUseCase.invoke() } returns Result.success(stubDrivesListResponse.drives)
        viewModel = DrivesListViewModel(drivesListUseCase)
    }

    @Test
    fun `Init Data Success`() {
        val state = viewModel.uiState.value
        assertEquals(state.drivesList, stubDrivesListResponse.drives)
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