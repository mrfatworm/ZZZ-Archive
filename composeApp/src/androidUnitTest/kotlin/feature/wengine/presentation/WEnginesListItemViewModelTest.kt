/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.wengine.presentation


import MainDispatcherRule
import feature.wengine.domain.WEnginesListUseCase
import feature.wengine.model.stubWEnginesList
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
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
        coEvery { wEnginesListUseCase.invoke() } returns flowOf(stubWEnginesList)
        every {
            wEnginesListUseCase.filterWEnginesList(
                any(), any(), any()
            )
        } returns stubWEnginesList.take(1)
        viewModel = WEnginesListViewModel(wEnginesListUseCase)
    }

    @Test
    fun `Init data`() {
        val state = viewModel.uiState.value
        assertEquals(stubWEnginesList, state.wEnginesList)
    }

    @Test
    fun `Filter rarity`() {
        viewModel.onAction(WEnginesListAction.ChangeRarityFilter(setOf(ZzzRarity.RARITY_S)))
        val state = viewModel.uiState.value
        assertEquals(44, state.filteredWEnginesList.first().id)
        assertEquals(1, state.filteredWEnginesList.size)
    }

    @Test
    fun `Filter specialty`() {
        viewModel.onAction(WEnginesListAction.ChangeSpecialtyFilter(setOf(AgentSpecialty.Support)))
        val state = viewModel.uiState.value
        assertEquals(44, state.filteredWEnginesList.first().id)
        assertEquals(1, state.filteredWEnginesList.size)
    }
}