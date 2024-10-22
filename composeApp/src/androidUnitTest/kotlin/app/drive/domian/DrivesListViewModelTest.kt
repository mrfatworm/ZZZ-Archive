/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: CC BY-SA 4.0
 */

package app.drive.domian


import MainDispatcherRule
import app.drive.data.FakeDriveRepository
import app.drive.domain.DrivesListViewModel
import app.drive.model.stubDriveListResponse
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
        assertEquals(state.drivesList, stubDriveListResponse.getDrivesNewToOld())
    }
}