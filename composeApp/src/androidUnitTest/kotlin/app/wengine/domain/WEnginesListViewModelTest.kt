/*
 * Copyright 2024 The ZZZ Archive Open Source Project by mrfatworm
 * License: MIT License
 */

package app.wengine.domain


import MainDispatcherRule
import app.wengine.data.FakeWEngineRepository
import app.wengine.model.stubWEnginesListResponse
import org.junit.Rule
import utils.AgentSpecialty
import utils.ZzzRarity
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class WEnginesListViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val wEngineRepository = FakeWEngineRepository()
    private lateinit var viewModel: WEnginesListViewModel

    @BeforeTest
    fun setup() {
        viewModel = WEnginesListViewModel(wEngineRepository)
    }

    @Test
    fun `Init Data Success`() {
        val state = viewModel.uiState.value
        assertEquals(state.wEnginesList, stubWEnginesListResponse.getWEnginesNewToOld())
    }

    @Test
    fun `Filter Rarity S Success`() {
        viewModel.rarityFilterChanged(setOf(ZzzRarity.RANK_S))
        val state = viewModel.uiState.value
        assertEquals(state.wEnginesList.first().id, 47)
        assertEquals(state.wEnginesList.size, 1)
    }

    @Test
    fun `Filter Specialty Support Success`() {
        viewModel.specialtyFilterChanged(setOf(AgentSpecialty.Support))
        val state = viewModel.uiState.value
        assertEquals(state.wEnginesList.first().id, 44)
        assertEquals(state.wEnginesList.size, 1)
    }
}