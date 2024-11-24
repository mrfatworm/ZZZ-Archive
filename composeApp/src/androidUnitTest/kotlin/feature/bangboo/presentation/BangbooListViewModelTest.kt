/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT
 */

package feature.bangboo.presentation


import MainDispatcherRule
import feature.bangboo.domain.BangbooListUseCase
import feature.bangboo.model.stubBangbooListResponse
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
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
        coEvery { bangbooListUseCase.invoke() } returns Result.success(stubBangbooListResponse.bangboo)
        every { bangbooListUseCase.filterBangbooList(any(), any(), any()) } returns listOf(
            stubBangbooListResponse.bangboo.first()
        ) // Penguinboo
        viewModel = BangbooListViewModel(bangbooListUseCase)
    }

    @Test
    fun `Init Data Success`() {
        val state = viewModel.uiState.value
        assertEquals(state.bangbooList, stubBangbooListResponse.bangboo)
    }

    @Test
    fun `Filter Rarity Success`() {
        viewModel.onAction(BangbooListAction.OnRarityFilterChanged(setOf(ZzzRarity.RANK_S)))
        val state = viewModel.uiState.value
        assertEquals(state.filteredBangbooList.first().id, 1) // Penguinboo
        assertEquals(state.filteredBangbooList.size, 1)
    }

    @Test
    fun `Filter Attribute Success`() {
        viewModel.onAction(BangbooListAction.OnAttributeFilterChanged(setOf(AgentAttribute.Ice)))
        val state = viewModel.uiState.value
        assertEquals(state.filteredBangbooList.first().id, 1) // Penguinboo
        assertEquals(state.filteredBangbooList.size, 1)
    }
}