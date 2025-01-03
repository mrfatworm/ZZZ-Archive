/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.drive.presentation


import MainDispatcherRule
import feature.drive.data.database.stubDrivesListItemEntity
import feature.drive.domain.DrivesListUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class DrivesListViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val drivesListUseCase = mockk<DrivesListUseCase>()
    private lateinit var viewModel: DrivesListViewModel

    @BeforeTest
    fun setup() {
        coEvery { drivesListUseCase.invoke() } returns flowOf(listOf(stubDrivesListItemEntity))
        viewModel = DrivesListViewModel(drivesListUseCase)
    }

    @Test
    fun `Init data success`() {
        val state = viewModel.uiState.value
        assertEquals(listOf(stubDrivesListItemEntity), state.drivesList)
    }

    @Test
    fun `Select drive`() {
        viewModel.onAction(DrivesListAction.ClickDriveDetail(1))
        val state = viewModel.uiState.value
        assertEquals(1, state.selectedDrive?.id)
    }

    @Test
    fun `GIVEN drive selected WHEN onDetailDismiss detail THEN selectedDrive is null`() {
        viewModel.onAction(DrivesListAction.ClickDriveDetail(1))
        viewModel.onAction(DrivesListAction.DismissDriveDetail)
        val state = viewModel.uiState.value
        assertNull(state.selectedDrive)
    }
}