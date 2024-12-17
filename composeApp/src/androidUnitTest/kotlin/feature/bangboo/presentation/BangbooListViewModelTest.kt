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
        assertEquals(stubBangbooList, state.bangbooList)
    }

    @Test
    fun `Filter rarity`() {
        viewModel.onAction(BangbooListAction.ChangeRarityFilter(setOf(ZzzRarity.RANK_S)))
        val state = viewModel.uiState.value
        assertEquals(1, state.filteredBangbooList.first().id) // Penguinboo
        assertEquals(1, state.filteredBangbooList.size)
    }

    @Test
    fun `Filter attribute`() {
        viewModel.onAction(BangbooListAction.ChangeAttributeFilter(setOf(AgentAttribute.Ice)))
        val state = viewModel.uiState.value
        assertEquals(1, state.filteredBangbooList.first().id) // Penguinboo
        assertEquals(1, state.filteredBangbooList.size)
    }
}