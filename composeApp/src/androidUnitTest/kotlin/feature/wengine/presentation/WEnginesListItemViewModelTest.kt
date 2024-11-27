/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.wengine.presentation


import MainDispatcherRule
import feature.wengine.domain.WEnginesListUseCase
import feature.wengine.model.stubWEnginesListResponse
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import org.junit.Rule
import utils.AgentSpecialty
import utils.ZzzRarity
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class WEnginesListItemViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val wEnginesListUseCase = mockk<WEnginesListUseCase>()
    private lateinit var viewModel: WEnginesListViewModel

    @BeforeTest
    fun setup() {
        coEvery { wEnginesListUseCase.invoke() } returns Result.success(stubWEnginesListResponse.wEngines)
        every { wEnginesListUseCase.filterWEnginesList(any(), any(), any()) } returns listOf(
            stubWEnginesListResponse.wEngines.first()
        )
        viewModel = WEnginesListViewModel(wEnginesListUseCase)
    }

    @Test
    fun `Init Data Success`() {
        val state = viewModel.uiState.value
        assertEquals(state.wEnginesList, stubWEnginesListResponse.wEngines)
    }

    @Test
    fun `Filter Rarity Success`() {
        viewModel.onAction(WEnginesListAction.OnRarityFilterChanged(setOf(ZzzRarity.RANK_S)))
        val state = viewModel.uiState.value
        assertEquals(state.filteredWEnginesList.first().id, 44)
        assertEquals(state.filteredWEnginesList.size, 1)
    }

    @Test
    fun `Filter Specialty Success`() {
        viewModel.onAction(WEnginesListAction.OnSpecialtyFilterChanged(setOf(AgentSpecialty.Support)))
        val state = viewModel.uiState.value
        assertEquals(state.filteredWEnginesList.first().id, 44)
        assertEquals(state.filteredWEnginesList.size, 1)
    }
}