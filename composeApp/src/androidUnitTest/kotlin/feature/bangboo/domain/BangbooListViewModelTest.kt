/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package feature.bangboo.domain


import MainDispatcherRule
import feature.bangboo.data.FakeBangbooRepository
import feature.bangboo.model.stubBangbooListResponse
import org.junit.Rule
import utils.AgentAttribute
import utils.ZzzRarity
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class BangbooListViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val bangbooRepository = FakeBangbooRepository()
    private lateinit var viewModel: BangbooListViewModel

    @BeforeTest
    fun setup() {
        viewModel = BangbooListViewModel(bangbooRepository)
    }

    @Test
    fun `Init Data Success`() {
        val state = viewModel.uiState.value
        assertEquals(state.bangbooList, stubBangbooListResponse.getBangbooNewToOld())
    }

    @Test
    fun `Filter Rarity S Success`() {
        viewModel.rarityFilterChanged(setOf(ZzzRarity.RANK_S))
        val state = viewModel.uiState.value
        assertEquals(state.bangbooList.first().id, 2)
        assertEquals(state.bangbooList.size, 1)
    }

    @Test
    fun `Filter Attribute Ice Success`() {
        viewModel.attributeFilterChanged(setOf(AgentAttribute.Ice))
        val state = viewModel.uiState.value
        assertEquals(state.bangbooList.first().id, 1)
        assertEquals(state.bangbooList.size, 1)
    }
}