/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.bangboo.presentation


import MainDispatcherRule
import feature.bangboo.domain.BangbooListUseCase
import feature.bangboo.model.stubBangbooList
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import utils.AgentAttribute
import utils.ZzzRarity
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class BangbooListViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val bangbooListUseCase = mockk<BangbooListUseCase>()
    private lateinit var viewModel: BangbooListViewModel

    @BeforeTest
    fun setup() {
        coEvery { bangbooListUseCase.invoke() } returns flowOf(stubBangbooList)
        every {
            bangbooListUseCase.filterBangbooList(any(), any(), any())
        } returns stubBangbooList.take(1) // Penguinboo
        viewModel = BangbooListViewModel(bangbooListUseCase)
    }

    @Test
    fun `Init data success`() {
        val state = viewModel.uiState.value
        assertEquals(state.bangbooList, stubBangbooList)
    }

    @Test
    fun `Filter rarity success`() {
        viewModel.onAction(BangbooListAction.OnRarityFilterChanged(setOf(ZzzRarity.RANK_S)))
        val state = viewModel.uiState.value
        assertEquals(state.filteredBangbooList.first().id, 1) // Penguinboo
        assertEquals(state.filteredBangbooList.size, 1)
    }

    @Test
    fun `Filter attribute success`() {
        viewModel.onAction(BangbooListAction.OnAttributeFilterChanged(setOf(AgentAttribute.Ice)))
        val state = viewModel.uiState.value
        assertEquals(state.filteredBangbooList.first().id, 1) // Penguinboo
        assertEquals(state.filteredBangbooList.size, 1)
    }
}